package api.dieti2024.controller;

import api.dieti2024.dto.DatiProfiloUtenteDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.util.ControllerRestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    private final ControllerRestUtil controllerUtil = new ControllerRestUtil();
    @Autowired
    private api.dieti2024.service.utenteService utenteService;
    @GetMapping("/datiProfilo")
      public DatiProfiloUtenteDTO getDatiProfilo(@RequestParam String email){
        return utenteService.getDatiProfilo(email);
    }
    @PutMapping("/datiProfilo")
    public ResponseEntity updateDatiProfilo( @RequestBody DatiProfiloUtenteDTO datiProfiloUtenteDTO){

        String email = controllerUtil.getEmailOfUtenteCorrente();

        try{
            utenteService.updateDatiProfilo(email,datiProfiloUtenteDTO);
            ResponseEntity resp = ResponseEntity.status(HttpStatus.OK).body("Dati aggiornati\n"+datiProfiloUtenteDTO);
            return resp;
        }catch (ApiException e) {
            String message = e.getMessage();
            HttpStatus status = e.getStatus();
            return ResponseEntity.status(status).body(message);
        }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
