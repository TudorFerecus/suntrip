package com.smartherd.suntrip;

// Interfata facuta cu scopul de a servi ca o notificare cand baza de date
// returneaza un raspuns la request
public interface VolleyCallback {
    void onSuccess(String response);
    void onError(String response);
}
