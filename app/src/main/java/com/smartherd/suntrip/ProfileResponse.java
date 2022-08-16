package com.smartherd.suntrip;

public class ProfileResponse {
    private int ok;
    private String error;
    private String nume_complet;
    private String email;
    private String new_token;

    public ProfileResponse(int ok_, String error_, String nume_complet_, String email_, String new_token_)
    {
        ok = ok_;
        error = error_;
        nume_complet = nume_complet_;
        email = email_;
        new_token = new_token_;
    }


    public int get_validation()
    {
        return ok;
    }
    public String get_error(){return error;}
    public String getNume_complet(){return nume_complet;}
    public String getEmail(){return email;}
    public String getNew_token(){return new_token;}


}
