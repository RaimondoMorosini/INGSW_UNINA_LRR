package com.lrr.Dieti23Server.dto;

import com.lrr.Dieti23Server.model.Utente;

public record DatiUtentePerTokenDTO(String email) {
    private DatiUtentePerTokenDTO(Utente utenteModel) {
        this(utenteModel.getEmail());
    }

    public static DatiUtentePerTokenDTO fromUserModel(Utente utenteModel) {
        return new DatiUtentePerTokenDTO(utenteModel);
    }
}
