package api.dieti2024.service;

import api.dieti2024.model.ProdottoInAsta;
import api.dieti2024.repository.ProdottoAstaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoAstaService {

    @Autowired
    private ProdottoAstaRepository prodottoAstaRepository;
    public List<ProdottoInAsta> getAllAste(){

        return prodottoAstaRepository.findAll();
    }
}
