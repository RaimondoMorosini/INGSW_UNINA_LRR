package lrr.server.controller;

import lrr.server.modello.ProdottoInAsta;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerRestProdotto {


    //Get all products && get product by i#@
    @GetMapping(path = "/prodotto")
    public List<ProdottoInAsta> getProdotto(
            @RequestParam int pagina,
            @RequestParam int numeroDiProdottiVisualizzati,
            @RequestParam String categoria
            ){
            //TODO: implementare la logica di visualizzazione dei prodotti

        return null;
    }

}
