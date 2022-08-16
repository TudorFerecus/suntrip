package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.smartherd.suntrip.databinding.ActivityLogInBinding;

public class LogIn extends UtilitiesClass {

    private ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_request(get_url(), new VolleyCallback() {
                    @Override
                    public void onSuccess(String response) {
                        //apel catre baza de date
                        LoginResponse loginResponse = responseToJSON(response, LoginResponse.class);
                        if(loginResponse == null)
                        {
                            Toast.makeText(LogIn.this, "Email / Password wrong", Toast.LENGTH_LONG).show();
                            return;
                        }
                        String responseString = loginResponse.get_error();

                        Toast.makeText(LogIn.this, responseString, Toast.LENGTH_LONG).show();

                        if(loginResponse.get_validation() == 1)
                        {

                            if(binding.cbRemember.isChecked())
                            {
                                remember_checkbox_data(true);
                                remember_data(binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
                            }
                            else if(checkbox.equals("false"))
                            {
                                remember_checkbox_data(false);
                                remember_data("", "");
                            }


                            Hash.getInstance().set_token(loginResponse.getToken());
                            switchActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onError(String response) {
                        Toast.makeText(LogIn.this, "The passwords do not match", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });

    }

    private String get_url() {
        return base_url_login + "&password=" + binding.etPassword.getText().toString() +
                "&email=" + binding.etEmail.getText().toString();

    }

    private void remember_data(String email, String password)
    {
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
        Log.e("LOGIN", "added data: " + email + ", " + password);
    }

    private void remember_checkbox_data(boolean state)
    {
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        if(state)
        {
            editor.putString("remember", "true");
        }

        else
        {
            editor.putString("remember", "false");
        }

        editor.apply();

    }

}