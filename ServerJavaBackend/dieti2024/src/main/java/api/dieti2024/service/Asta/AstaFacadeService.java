package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AstaFacadeService {
    @Autowired
    ProdottoService prodottoService;
    @Autowired
    AstaService astaService;
    public void creaAsta(CreaAstaDTO datiInput) {

        checkDatiInputValidi(datiInput);

    }

    private void checkDatiInputValidi(CreaAstaDTO datiInput) {
        if (datiInput == null) {
            throw new ApiException("Dati input mancanti", HttpStatus.BAD_REQUEST);
        }
        astaService.checkDatiInputi(datiInput.datiAsta());

    }
}
