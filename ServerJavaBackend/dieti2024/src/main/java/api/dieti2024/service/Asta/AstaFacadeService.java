package api.dieti2024.service.Asta;

import api.dieti2024.dto.asta.CreaAstaDTO;
import api.dieti2024.dto.asta.InputAstaDTO;
import api.dieti2024.exceptions.ApiException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AstaFacadeService {
    @Autowired
    ProdottoService prodottoService;
    @Autowired
    AstaService astaService;
    @Transactional
    public void creaAsta(CreaAstaDTO datiInput) {
            checkDatiInputValidi(datiInput);
            int idProdotto = prodottoService.salvaProdotto(datiInput.datiProdotto());
            astaService.salvaAsta(datiInput.datiAsta(), idProdotto);
//            saveDatiExtraAsta(datiInput.datiAsta());
        System.out.println("Asta creata con successo");
    }

    private void saveDatiExtraAsta(InputAstaDTO inputAstaDTO) {
        //TODO salvare dati extra asta se necessario
    }

    private void checkDatiInputValidi(CreaAstaDTO datiInput) {
        if (datiInput == null) {
            throw new ApiException("Dati input mancanti", HttpStatus.BAD_REQUEST);
        }
        //TODO aggiungere controlli per vedere se utente ha il permesso di creare asta
        astaService.checkDatiInputi(datiInput.datiAsta());
        prodottoService.checkDatiInputi(datiInput.datiProdotto());

    }
}
