package api.dieti2024.util;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {
    final SimpMessagingTemplate simpleMessagingTemplate;

    public WebSocketUtil(SimpMessagingTemplate simpleMessagingTemplate) {
        this.simpleMessagingTemplate = simpleMessagingTemplate;
    }
    //inviaMessaggio a una socket specifica
    public void inviaMessaggio(String messaggio, String destinatario) {
        simpleMessagingTemplate.convertAndSend(destinatario, messaggio);
    }
}
