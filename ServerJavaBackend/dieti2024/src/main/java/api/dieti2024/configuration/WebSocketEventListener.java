package api.dieti2024.configuration;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final UtentiConnessi utentiConnessi;

    public WebSocketEventListener(UtentiConnessi utentiConnessi) {
        this.utentiConnessi = utentiConnessi;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = event.getSessionId();
        // Rimuovi il sessionId dalla tua lista di sessioni attive
        utentiConnessi.rimuoviIdSessione(sessionId);
        System.out.println("Sessione disconnessa: " + sessionId);
    }
}
