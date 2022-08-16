package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.smartherd.suntrip.databinding.ActivityRouteListBinding;

import java.util.ArrayList;

public class RouteList extends AppCompatActivity {

    private ActivityRouteListBinding binding;
    private ArrayList<RouteItem> routeItems;
    private String zone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getExtrass();
        Log.e("ROUTE LIST", zone);
        routeItems = new ArrayList<>();

        setRouteInfo();
        setAdapter();
    }

    private void setAdapter()
    {
        recycleRouteAdapter adapter = new recycleRouteAdapter(routeItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvRoutes.setLayoutManager(layoutManager);
        binding.rvRoutes.setItemAnimator(new DefaultItemAnimator());
        binding.rvRoutes.setAdapter(adapter);
    }

    private void setRouteInfo()
    {
        routeItems.add(new RouteItem("AAAA", "BBBB"));
        routeItems.add(new RouteItem("CCCC", "DDDD"));
        routeItems.add(new RouteItem("EEEE", "FFFF"));
        routeItems.add(new RouteItem("GGGG", "HHHH"));
    }

    private void getExtrass()
    {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            zone = extras.getString("zone");
    }

}