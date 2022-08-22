package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.smartherd.suntrip.databinding.ActivityRouteListBinding;

import java.util.ArrayList;
import java.util.List;

public class RouteList extends UtilitiesClass {

    private ActivityRouteListBinding binding;
    private ArrayList<RouteItem> routeItems;
    private String zone = "";
    recycleRouteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getExtrass();
        Log.e("ROUTE LIST", zone);
        routeItems = new ArrayList<>();
        String cap = zone.substring(0, 1).toUpperCase() + zone.substring(1);
        binding.tvZone.setText(cap);

        setRouteInfo();
    }

    private void setAdapter()
    {
        adapter = new recycleRouteAdapter(routeItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvRoutes.setLayoutManager(layoutManager);
        binding.rvRoutes.setItemAnimator(new DefaultItemAnimator());
        binding.rvRoutes.setAdapter(adapter);
    }

    private void setRouteInfo()
    {
        routeItems.add(new RouteItem("AAAA", "BBBB", "https://images.unsplash.com/photo-1589182373726-e4f658ab50f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"));

        setAdapter();

        adapter.removeItem(0);


        get_request(get_url(), new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();

                ZoneResponse[] routeArray = gson.fromJson(response, ZoneResponse[].class);

                for(ZoneResponse route : routeArray) {
                    adapter.addItem(0,new RouteItem(route.getTitlu_ruta(), route.getText_content(), "https://images.unsplash.com/photo-1589182373726-e4f658ab50f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80"));
                }
            }

            @Override
            public void onError(String response) {

            }
        });

    }

    private void getExtrass()
    {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            zone = extras.getString("zone");
    }

    private String get_url()
    {
        return base_url_zone + "nume_zona=" + "zona_" + zone;

    }


}