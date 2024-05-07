package api.dieti2024.websocketTest;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class controllerFaiOffertaWebSocket {

    @Autowired
    SimpMessagingTemplate simpleMessagingTemplate;

    @GetMapping("public/inviamsg")
    public String faiOfferta() {
        simpleMessagingTemplate.convertAndSend("/asta", "un tizio ha detto: ");
        return "qualcosa ho fatto poi sti cazzi";
    }

}
