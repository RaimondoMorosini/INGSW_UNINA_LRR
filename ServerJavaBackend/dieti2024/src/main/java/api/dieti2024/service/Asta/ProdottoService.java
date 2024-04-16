package api.dieti2024.service.Asta;

import api.dieti2024.astaTmp.*;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.dto.DatiCreazioneAstaDTODEPRECATO;
import api.dieti2024.exceptions.ApiException;
import api.dieti2024.model.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private api.dieti2024.repository.ProdottoService prodottoServiceService;
    public List<Prodotto> getAllAste(){

        return prodottoServiceService.findAll();
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
}
