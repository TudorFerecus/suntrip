package com.smartherd.suntrip;

//stocarea interna a aplicatiei
public class Hash {
    private static Hash instance = null;
    public String token = "";

    public static Hash getInstance()
    {
        if(instance == null)
        {
            instance = new Hash();
        }
        return instance;
    }

    public String get_token()
    {
        return token;
    }

    public void set_token(String token_)
    {
        token = token_;
    }



}
