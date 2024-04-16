package api.dieti2024.astaTmp;

import api.dieti2024.dto.DatiCreazioneAstaDTODEPRECATO;
import api.dieti2024.model.Offerta;

public interface AstaStrategy {
     void creaAsta(DatiCreazioneAstaDTODEPRECATO datiPerCreazioneDtoInput);
     void  addOfferta(Offerta offerta);
     Offerta getOfferteMigliore();

     boolean isAstaChiusa();

}
