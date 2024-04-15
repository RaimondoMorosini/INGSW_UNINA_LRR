package api.dieti2024.controller;

import api.dieti2024.model.ProdottoInAsta;
import api.dieti2024.service.ProdottoAstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ProdottoAstaController {

    @Autowired
    ProdottoAstaService prodottoAstaService;

    @GetMapping("/getAllAste")
    public List<ProdottoInAsta> getAllAste(){

        return prodottoAstaService.getAllAste();
    }
}
