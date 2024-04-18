package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.InfoProdottoPerCreazioneDTO;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.dto.DatiCreazioneAstaDTODEPRECATO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Prodotto;
import api.dieti2024.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    public List<Prodotto> getAllAste(){

        return prodottoRepository.findAll();
    }


    public void checkDatiInputi(InfoProdottoPerCreazioneDTO datiDto) {
        if (datiDto == null) {
            throw new ApiException("Dati asta mancanti", HttpStatus.BAD_REQUEST);
        }
    }

    public int salvaProdotto(InfoProdottoPerCreazioneDTO datiDTO) {
        Prodotto prodotto = datiDTO.toProdotto();
        return prodottoRepository.save(prodotto).getId();
    }
}
