package api.dieti2024.configuration;


import api.dieti2024.exceptions.ApiException;
import api.dieti2024.security.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JwtHandshakeInterceptor implements ChannelInterceptor {

     final
     JWTUtils jwtUtils;
     final
     UtentiConnessi utentiConnessi;

    public JwtHandshakeInterceptor(JWTUtils jwtUtils, UtentiConnessi utentiConnessi) {
        this.jwtUtils = jwtUtils;
        this.utentiConnessi = utentiConnessi;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(message);
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            if(headerAccessor.getDestination().contains("notifichePersonali")){
                String sessionID = headerAccessor.getSessionId();
                String authorizationHeader = headerAccessor.getFirstNativeHeader("Authorization");
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    throw new ApiException("Errore nell'intestazione 'Authorization': assicurati di includere un token valido che inizi con 'Bearer ' e che non sia scaduto.", HttpStatus.BAD_REQUEST);
                }

                String token = authorizationHeader.substring(7);
                if(!jwtUtils.isTokenValid(token)){
                    throw new ApiException("Il token fornito non è valido. Controlla se è scaduto o se è stato modificato.", HttpStatus.UNAUTHORIZED);
                }
                try {
                    String usernameFromDestination= getEmailFromDestination(headerAccessor);
                    String usernameFromToken= jwtUtils.getUsername(token);
                    if(usernameFromToken.compareToIgnoreCase(usernameFromDestination)!=0){
                        throw new ApiException("TODO",HttpStatus.NOT_IMPLEMENTED);
                    }
                    utentiConnessi.aggiungiUtente(usernameFromToken,headerAccessor.getSessionId());

                }catch (Exception e) {
                    throw new ApiException("TODO",HttpStatus.NOT_IMPLEMENTED);
                }
            }


        } else if (StompCommand.DISCONNECT.equals(headerAccessor.getCommand())){
                utentiConnessi.rimuoviIdSessione(headerAccessor.getSessionId());
        }
        return message;
    }

    private String getEmailFromDestination(StompHeaderAccessor headerAccessor) {
        String input = headerAccessor.getDestination();

        // Espressione regolare per trovare l'email
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();

        } else {
            return "";
        }
    }


}