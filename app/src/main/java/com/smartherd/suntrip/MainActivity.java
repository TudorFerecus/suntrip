package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.smartherd.suntrip.databinding.ActivityMainBinding;

public class MainActivity extends UtilitiesClass {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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