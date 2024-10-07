package api.dieti2024.websockettest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class HeartbeatController {

    @MessageMapping("/heartbeat")
    @SendToUser("/queue/heartbeat")
    public String handleHeartbeat(String message) {
        System.out.println("Heartbeat ricevuto: " + message);
        // Aggiungi eventuale logica necessaria qui, se necessario
        return "EHLO";
    }
}
