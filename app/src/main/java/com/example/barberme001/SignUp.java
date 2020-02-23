package com.example.barberme001;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class SignUp extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button button_register;

    final String url_Register = "https://barberme.000webhostapp.com/register_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = etName.getText().toString();
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();
            }
        });

    }



}


