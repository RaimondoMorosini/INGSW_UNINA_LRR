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
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // Verifica se il comando è SUBSCRIBE
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            if (headerAccessor.getDestination().contains("notifichePersonali")) {
                String sessionID = headerAccessor.getSessionId();
                String authorizationHeader = headerAccessor.getFirstNativeHeader("Authorization");

                // Controlla la presenza e la validità dell'intestazione 'Authorization'
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    throw new ApiException("Errore nell'intestazione 'Authorization': assicurati di includere un token valido che inizi con 'Bearer ' e che non sia scaduto.", HttpStatus.BAD_REQUEST);
                }

                String token = authorizationHeader.substring(7);

                // Verifica la validità del token
                if (!jwtUtils.isTokenValid(token)) {
                    throw new ApiException("Il token fornito non è valido. Controlla se è scaduto o se è stato modificato.", HttpStatus.UNAUTHORIZED);
                }

                try {
                    // Estrae l'username dalla destinazione e dal token
                    String usernameFromDestination = getEmailFromDestination(headerAccessor);
                    String usernameFromToken = jwtUtils.getUsername(token);

                    // Confronta i due username
                    if (!usernameFromToken.equalsIgnoreCase(usernameFromDestination)) {
                        throw new ApiException("Utente non autorizzato alla sottoscrizione alla destinazione specificata.", HttpStatus.FORBIDDEN);
                    }

                    // Aggiungi l'utente alla lista degli utenti connessi
                    utentiConnessi.aggiungiUtente(usernameFromToken, sessionID);

                } catch (Exception e) {
                    throw new ApiException("Errore durante la gestione della sottoscrizione.", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            // Verifica se il comando è DISCONNECT
        } else if (StompCommand.DISCONNECT.equals(headerAccessor.getCommand())) {
            utentiConnessi.rimuoviIdSessione(headerAccessor.getSessionId());
        }

        // Se tutto è andato a buon fine, richiama il metodo della classe base
        return ChannelInterceptor.super.preSend(message, channel);
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