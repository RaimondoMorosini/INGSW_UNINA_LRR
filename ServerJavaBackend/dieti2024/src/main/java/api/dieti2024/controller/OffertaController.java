package api.dieti2024.controller;

import api.dieti2024.dto.OffertaDto;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.service.OffertaService;
import api.dieti2024.util.CalendarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OffertaController {

    @Autowired
    SimpMessagingTemplate simpleMessagingTemplate;

    @Autowired
    OffertaService offertaService;

    @PostMapping("/faiOfferta")
    public ResponseEntity <Long> faiOfferta(@RequestBody OffertaDto offertaDto) {

        long tempoOfferta = CalendarioUtil.GetTime();

        try {
            long tempoScadenza = offertaService.faiOfferta(offertaDto, tempoOfferta ) ;
            simpleMessagingTemplate.convertAndSend("/asta/"+offertaDto.idAsta(), "Un utente ha fatto un'offerta scade asta:"+tempoScadenza);
            return ResponseEntity.ok(tempoScadenza);
        }catch (ApiException e){
            throw e;
        }
        catch (Exception e){
            throw new ApiException("Errore durante la creazione dell'offerta", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
