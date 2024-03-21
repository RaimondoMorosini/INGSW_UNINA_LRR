package api.dieti2024.configuration;

import api.dieti2024.dto.NotificaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class webSocketEventListener {

    @EventListener
    public void HandleWebSocketDisconnect(SessionDisconnectEvent event) {
        log.info("WebSocket Disconnected");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String email = headerAccessor.getSessionAttributes().get("email").toString();
        if (email != null) {
            log.info("User Disconnected : " + email);
            var notificaDTO = NotificaDTO.builder()
                    .email(email)
                    .messaggio("User Disconnected")
                    .build();
        }

    }
}
