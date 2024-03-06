package com.lrr.Dieti23Server.dto;

public class GoogleTokenInfo {

    private String iss;
    private String sub;
    private String azp;
    private String aud;
    private String iat;
    private String exp;
    private String email;
    private boolean email_verified;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;

    public String getAud() {
        return aud;
    }

    public String getEmail() {
        return email;
    }
}
