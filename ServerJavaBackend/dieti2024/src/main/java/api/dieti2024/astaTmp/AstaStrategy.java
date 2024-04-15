package api.dieti2024.astaTmp;

import api.dieti2024.model.Offerta;

public interface AstaStrategy {
     void creaAsta();
     void  addOfferta(Offerta offerta);
     Offerta getOfferteMigliore();

     boolean isAstaChiusa();

}
