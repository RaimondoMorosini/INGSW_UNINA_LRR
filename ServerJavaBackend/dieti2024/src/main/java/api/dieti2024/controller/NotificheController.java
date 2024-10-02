package api.dieti2024.controller;

import api.dieti2024.dto.NotificaDTO;
import api.dieti2024.service.NotificaService;
import api.dieti2024.util.ControllerRestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificheController {

    private final NotificaService notificaService;

    public NotificheController(NotificaService notificaService) {
        this.notificaService = notificaService;
    }


    @GetMapping("/numeroNotificheTotali")
    public int getNumeroNotificheTotali() {
        String email = ControllerRestUtil.getEmailOfUtenteCorrente();
        return notificaService.getNumeroNotificheTotali(email);
    }
    @GetMapping("/numeroNotificheNonVisualizzate")
    public int getNumeroNotificheNonVisualizzate() {
       String email = ControllerRestUtil.getEmailOfUtenteCorrente();
        return notificaService.getNumeroNotificheNonVisualizzate(email);
    }
    @GetMapping("/notificheTotali")
    public List<NotificaDTO> getNotificheTotali(@RequestParam int numeroElementi, @RequestParam int numeroPagina) {
        String email = ControllerRestUtil.getEmailOfUtenteCorrente();
        return notificaService.getNotificheUtente(email, numeroElementi, numeroPagina);

    }
    @GetMapping("/notificheNonVisualizzate")
    public List<NotificaDTO> getNotificheNonVisualizzate(@RequestParam int numeroElementi, @RequestParam int numeroPagina) {
        String email = ControllerRestUtil.getEmailOfUtenteCorrente();
        return notificaService.getNotificheNonVisualizzateUtente(email, numeroElementi, numeroPagina);
    }

}
