package api.dieti2024.controller;

import api.dieti2024.dto.DatiCreazioneAstaDTO;
import api.dieti2024.model.ProdottoInAsta;
import api.dieti2024.service.ProdottoAstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin
@RequestMapping("/asta")
public class ProdottoAstaController {

    @Autowired
    ProdottoAstaService prodottoAstaService;

    @GetMapping("/getAllAste")
    public List<ProdottoInAsta> getAllAste(){

        return prodottoAstaService.getAllAste();
    }

    @PostMapping("/addProdottoAsta")
    public void addProdottoAsta(@RequestBody DatiCreazioneAstaDTO datiCreazioneDto){
        double baseAsta;
        double prezzo_attuale;
        long data_scadenza;
        long data_inizio;
        String nome_prodotto  ;
        String descrizione;
        String categoria;
        List<String> immagini = new ArrayList<>();
        String tipoAsta;


    }

}
