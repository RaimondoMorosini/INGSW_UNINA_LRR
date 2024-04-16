package api.dieti2024.astaTmp;

import api.dieti2024.dto.DatiCreazioneAstaDTODEPRECATO;
import api.dieti2024.model.Offerta;
import api.dieti2024.repository.ProdottoRepository;
import org.springframework.stereotype.Component;

@Component
public class AstaIngleseService implements AstaStrategy{

private ProdottoRepository prodottoRepository;

    @Override
    public void creaAsta(DatiCreazioneAstaDTODEPRECATO datiPerCreazioneDtoInput) {

    }

    @Override
    public void addOfferta(Offerta offerta) {

    }

    @Override
    public Offerta getOfferteMigliore() {
        return null;
    }

    @Override
    public boolean isAstaChiusa() {
        return false;
    }
}
