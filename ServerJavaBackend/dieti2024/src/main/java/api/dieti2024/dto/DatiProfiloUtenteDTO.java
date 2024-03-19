package api.dieti2024.dto;

import api.dieti2024.model.Utente;

public record DatiProfiloUtenteDTO (String nome, String cognome, String siti, String areaGeografica, String bio) {
    private DatiProfiloUtenteDTO(Utente utenteModel) {
        this(utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getSiti(), utenteModel.getAreaGeografica(), utenteModel.getBio());
    }

    public static DatiProfiloUtenteDTO fromUserModel(Utente utenteModel) {
        return new DatiProfiloUtenteDTO(utenteModel);
    }
}
