package api.dieti2024.websocketTest;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class controllerFaiOffertaWebSocket {

    @Autowired
    SimpMessagingTemplate simpleMessagingTemplate;

    @GetMapping("/inviamsg")
    public String faiOfferta(@RequestParam String msg) {
        simpleMessagingTemplate.convertAndSend("/asta", "un tizio ha detto: " + msg);
        return "qualcosa ho fatto poi sti cazzi";
    }

}
