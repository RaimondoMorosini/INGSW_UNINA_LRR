package api.dieti2024.astaTmp;

import api.dieti2024.dto.DatiCreazioneAstaDTODEPRECATO;
import api.dieti2024.model.Offerta;

public class AstaSilenziosaService implements AstaStrategy {
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
