package com.smartherd.suntrip;

import static android.view.Gravity.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smartherd.suntrip.databinding.ActivityMainBinding;

public class MainActivity extends UtilitiesClass{

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navBarSetUp(binding.toolbar, binding.drawerLayout);

        navSetUp(binding.nv, new NavigationViewInterface() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void OnItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.itHome:
                        break;

                    case R.id.itRoutePicker:
                        switchActivity(RoutePicker.class);
                        break;

                    case R.id.itProfile:
                        switchActivity(Profile.class);
                        break;


                }
            }
        });

        if(Hash.getInstance().get_token().equals(""))
        {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            String checkbox = preferences.getString("remember", "");

            if (checkbox.equals("false")) {
                switchActivity(LogIn.class);
                finish();
            }
            else if(checkbox.equals("true"))
            {
                String password = preferences.getString("password", "");
                String email = preferences.getString("email", "");

                get_request(get_login_url(password, email), new VolleyCallback() {
                    @Override
                    public void onSuccess(String response) {
                        LoginResponse loginResponse = responseToJSON(response, LoginResponse.class);
                        String responseString = loginResponse.get_error();
                        Toast.makeText(MainActivity.this, responseString, Toast.LENGTH_LONG).show();

                        if(loginResponse.get_validation() == 1)
                        {
                            Hash.getInstance().set_token(loginResponse.getToken());
                        }

                    }

                    @Override
                    public void onError(String response) {
                        Toast.makeText(MainActivity.this, "The passwords do not match", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }
        binding.btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
            }
        });

        binding.btnRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity(RoutePicker.class);
            }
        });


    }



    private String get_login_url(String password, String email)
    {
        Log.e("MAINAPP",base_url_login + "&password=" + password +
                "&email=" + email );
        return base_url_login + "&password=" + password +
                "&email=" + email;
    }


}