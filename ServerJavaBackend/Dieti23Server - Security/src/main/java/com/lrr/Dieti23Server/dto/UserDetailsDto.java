package com.lrr.Dieti23Server.dto;

import com.lrr.Dieti23Server.model.Utente;

public record UserDetailsDto(String email, String password) {
    private UserDetailsDto(Utente utenteModel) {
        this(utenteModel.getEmail(), utenteModel.getPassword());
    }

    public static UserDetailsDto fromUserModel(Utente userModel) {
        return new UserDetailsDto(userModel);
    }
}