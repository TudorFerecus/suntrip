package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.smartherd.suntrip.databinding.ActivityRoutePickerBinding;

public class RoutePicker extends UtilitiesClass {

    private ActivityRoutePickerBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoutePickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navBarSetUp(binding.toolbar, binding.drawerLayout);
        navSetUp(binding.nv, new NavigationViewInterface() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void OnItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.itHome:
                        switchActivity(MainActivity.class);
                        finish();
                        break;

                    case R.id.itRoutePicker:
                        break;

                    case R.id.itProfile:
                        switchActivity(Profile.class);
                        finish();
                        break;


                }
            }
        });

        binding.btnBanat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("banat");
            }
        });

        binding.btnBucovina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("bucovina");
            }
        });

        binding.btnCrisana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("crisana");
            }
        });

        binding.btnDobrogea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("dobrogea");
            }
        });

        binding.btnMaramures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("maramures");
            }
        });

        binding.btnMoldova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("moldova");
            }
        });

        binding.btnMuntenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("muntenia");
            }
        });

        binding.btnOltenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("oltenia");
            }
        });

        binding.btnTransilvania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSceneToRouteList("transilvania");
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View View){ switchActivity(MainActivity.class);}
        });

    }


    private void changeSceneToRouteList(String zone)
    {
        Intent i = new Intent(this, RouteList.class);
        i.putExtra("zone", zone);
        startActivity(i);
        finish();
    }

}