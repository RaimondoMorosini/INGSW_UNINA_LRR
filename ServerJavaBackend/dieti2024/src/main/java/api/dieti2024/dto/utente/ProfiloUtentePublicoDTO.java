package api.dieti2024.dto.utente;

import api.dieti2024.model.DatiVenditori;
import api.dieti2024.model.Utente;

public record ProfiloUtentePublicoDTO(String nome, String cognome, String siti, String areaGeografica, String bio, String foto_profilo, Boolean isVenditore,
                                      DatiVenditori datiVenditore) {
    private ProfiloUtentePublicoDTO(Utente utenteModel, boolean isVenditore) {
        this(utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getSiti(), utenteModel.getAreaGeografica(), utenteModel.getBio(),utenteModel.getFotoProfilo(),isVenditore,null);
    }
    private ProfiloUtentePublicoDTO(Utente utenteModel, boolean isVenditore, DatiVenditori datiVenditori) {
        this(utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getSiti(), utenteModel.getAreaGeografica(), utenteModel.getBio(),utenteModel.getFotoProfilo(),isVenditore,datiVenditori);
    }
    public static ProfiloUtentePublicoDTO fromUserModel(Utente utenteModel,boolean isVenditore) {
        return new ProfiloUtentePublicoDTO(utenteModel , isVenditore);
    }
    public static ProfiloUtentePublicoDTO fromUserModel(Utente utenteModel,boolean isVenditore, DatiVenditori datiVenditori) {
        return new ProfiloUtentePublicoDTO(utenteModel , isVenditore, datiVenditori);
    }
}
