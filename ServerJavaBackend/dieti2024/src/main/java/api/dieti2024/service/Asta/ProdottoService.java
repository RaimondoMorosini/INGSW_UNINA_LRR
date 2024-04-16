package api.dieti2024.service.Asta;

import api.dieti2024.astaTmp.*;
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

    public void creaAsta(DatiCreazioneAstaDTODEPRECATO datiPerCreazioneDtoInput) {

        //salvataggio nel db del prodotto in asta
        api.dieti2024.model.Prodotto prodottoInAsta = new api.dieti2024.model.Prodotto();



        InputAstaDTO datiAstaDto = datiPerCreazioneDtoInput.datiAsta();
        AstaStrategy astaService = setContextAsta(datiAstaDto.tipoAsta());
        astaService.creaAsta(datiPerCreazioneDtoInput);

    }

    private AstaStrategy setContextAsta(String tipoAsta) {

        switch (tipoAsta){
            case TipoAsta.INGLESE:
                return new AstaIngleseService();
            case TipoAsta.SILENZIOSA:
                return new AstaSilenziosaService();
            case TipoAsta.APPALTO:
                return new AstaAppaltoService();
            default:
                throw new ApiException("Tipo asta non valido", HttpStatus.BAD_REQUEST);
        }
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
