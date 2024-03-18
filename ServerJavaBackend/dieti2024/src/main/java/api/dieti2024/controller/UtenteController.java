package api.dieti2024.controller;

import api.dieti2024.dto.DatiProfiloUtenteDTO;
import api.dieti2024.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private api.dieti2024.service.utenteService utenteService;
    @GetMapping("/datiProfilo")
      public DatiProfiloUtenteDTO getDatiProfilo(@RequestParam String email){
        return utenteService.getDatiProfilo(email);
    }
    @PutMapping("/datiProfilo/{email}")
    public ResponseEntity updateDatiProfilo(String email, @RequestBody DatiProfiloUtenteDTO datiProfiloUtenteDTO){
        try{
            utenteService.updateDatiProfilo(email,datiProfiloUtenteDTO);
            return ResponseEntity.ok().build();
        }catch (ApiException e) {
            String message = e.getMessage();
            HttpStatus status = e.getStatus();
            return ResponseEntity.status(status).body(message);
        }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
