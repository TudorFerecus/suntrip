package com.smartherd.suntrip;

//clasa de tip blueprint care imita structura json-ului dat de baza de date
public class RegisterResponse {
    private String error;
    private int ok;

    public RegisterResponse(String error_, int ok_)
    {
        error = error_;
        ok = ok_;
    }

    public String get_error()
    {
        return error;
    }

    public int get_validation()
    {
        return ok;
    }


}
