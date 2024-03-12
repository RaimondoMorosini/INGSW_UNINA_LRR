package com.lrr.Dieti23Server.dto;

import com.lrr.Dieti23Server.model.Utente;

public record CredenzialiUtenteDTO(String email, String password) {
    private CredenzialiUtenteDTO(Utente utenteModel) {
        this(utenteModel.getEmail(), utenteModel.getPassword());
    }

    public static CredenzialiUtenteDTO fromUserModel(Utente utenteModel) {
        return new CredenzialiUtenteDTO(utenteModel);
    }
}
