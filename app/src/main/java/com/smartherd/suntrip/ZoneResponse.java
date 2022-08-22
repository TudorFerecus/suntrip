package com.smartherd.suntrip;

public class ZoneResponse {
    private int id_ruta;
    private String id_table_locatii;
    private String titlu_ruta;
    private String text_content;
    private String cdn_poza;
    private int nr_locatii;

    public ZoneResponse(int id_ruta_, String id_table_locatii_,String titlu_ruta_, String text_content_,
                        String cdn_poza_, int nr_locatii_)
    {
        id_ruta = id_ruta_;
        id_table_locatii = id_table_locatii_;
        titlu_ruta = titlu_ruta_;
        text_content = text_content_;
        cdn_poza = cdn_poza_;
        nr_locatii = nr_locatii_;
    }

    public int getId_ruta()
    {
        return id_ruta;
    }

    public int getNr_locatii()
    {
        return nr_locatii;
    }

    public String getCdn_poza()
    {
        return cdn_poza;
    }

    public String getId_table_locatii()
    {
        return id_table_locatii;
    }

    public String getText_content()
    {
        return text_content;
    }

    public String getTitlu_ruta()
    {
        return titlu_ruta;
    }
}
