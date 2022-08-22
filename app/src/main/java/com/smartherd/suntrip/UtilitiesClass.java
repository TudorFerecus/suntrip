package com.smartherd.suntrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

//clasa de utilitati facuta cu scopul de a nu repeta cod
public class UtilitiesClass extends AppCompatActivity {

    protected final String base_url_register = "https://identify-solutions.eu/trip_api.php?type=1";
    protected final String base_url_login = "https://identify-solutions.eu/trip_api.php?type=2";
    protected final String base_url_profile = "https://identify-solutions.eu/api_get_data.php?";
    protected final String base_url_zone = "https://identify-solutions.eu/sun_api/get_content_zona.php?";


    protected <T> void switchActivity(Class<T> newActivity)
     {
         Intent intent = new Intent(getApplicationContext(), newActivity);
         startActivity(intent);
     }

     // transformarea unui string intr-un json de tipul clasei de date data
    protected <T> T responseToJSON(String response, Class<T> dataClass)
    {
        Gson g = new Gson();
        return g.fromJson(response, dataClass);
    }

    //face request de tip get bazei de date
    protected void get_request(String url_, final VolleyCallback callback)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url_, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // daca merge, notifica interfata
                        callback.onSuccess(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // daca nu merge, notifica interfata
                        callback.onError(error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }

    protected void navBarSetUp(Toolbar toolbar, DrawerLayout drawerLayout)
    {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    protected void navSetUp(NavigationView navigationView, final NavigationViewInterface nvInterface)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                nvInterface.OnItemSelected(item);
                return false;
            }
        });
    }



}
