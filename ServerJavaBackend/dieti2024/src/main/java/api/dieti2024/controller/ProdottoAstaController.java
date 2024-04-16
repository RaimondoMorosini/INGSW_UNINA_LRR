package api.dieti2024.controller;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Prodotto;
import api.dieti2024.service.Asta.AstaFacadeService;
import api.dieti2024.service.Asta.AstaService;
import api.dieti2024.service.Asta.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/asta")
public class ProdottoAstaController {

    @Autowired
    ProdottoService prodottoService;
    @Autowired
    AstaService astaService;

    @Autowired
    AstaFacadeService astaFacadeService;
    @GetMapping("/getAllAste")
    public List<Prodotto> getAllAste(){

        return prodottoService.getAllAste();
    }


    @PostMapping("/creaasta")
    public ResponseEntity addProdottoAsta(@RequestBody CreaAstaDTO datiPerCreazioneDtoInput){
        try {
            astaFacadeService.creaAsta(datiPerCreazioneDtoInput);
            return ResponseEntity.ok().build();
        }catch(ApiException e){
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno al server");
        }


    }

}
