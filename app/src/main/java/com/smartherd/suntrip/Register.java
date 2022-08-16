package com.smartherd.suntrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.smartherd.suntrip.databinding.ActivityRegisterBinding;

import com.android.volley.toolbox.StringRequest;


public class Register extends UtilitiesClass {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifica daca parolele corespund
                if (binding.etPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString())) {
                    //apel catre baza de date
                    get_request(get_url(), new VolleyCallback() {
                        @Override
                        public void onSuccess(String response) {
                            RegisterResponse responseRegister = responseToJSON(response, RegisterResponse.class);
                            String responseString = responseRegister.get_error();

                            Toast.makeText(Register.this, responseString, Toast.LENGTH_LONG).show();
                            if (responseRegister.get_validation() == 1)
                                switchActivity(LogIn.class);

                        }

                        @Override
                        public void onError(String response) {
                            Log.e("REGISTER", response);
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "The passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private String get_url()
    {
        return base_url_register + "&password=" + binding.etPassword.getText().toString() + "&email=" +
                binding.etEmail.getText().toString() + "&name=" + binding.etName.getText().toString() +
                "&surname=" + binding.etSurname.getText().toString();

    }


}