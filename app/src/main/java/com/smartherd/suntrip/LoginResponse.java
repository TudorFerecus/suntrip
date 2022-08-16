package com.smartherd.suntrip;

//clasa de tip blueprint care imita structura json-ului dat de baza de date
public class LoginResponse {
    private String error;
    private int ok;
    private String fullName;
    private String token;

    public LoginResponse(String error_, int ok_, String fullName_, String token_)
    {
        error = error_;
        ok = ok_;
        fullName = fullName_;
        token = token_;
    }

    public String get_error()
    {
        return error;
    }

    public int get_validation()
    {
        return ok;
    }

    public String get_name() {return fullName;}

    public String getToken() {return token;}
}
