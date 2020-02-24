package com.example.barberme001;

import androidx.appcompat.app.AppCompatActivity;


import com.example.helpers.InputValidation;
import com.example.model.User;
import com.example.sql.DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = MainActivity.this;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button blogin;
    private Button bregister;

    private InputValidation inputValidation;
    private DatabaseHelper  databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editText1);
        editTextPassword = findViewById(R.id.editText2);

        blogin = findViewById(R.id.login);
        bregister = findViewById(R.id.register);

        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);

        blogin.setOnClickListener(this);
        bregister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Intent intent = new Intent(activity, SignUp.class);
                startActivity(intent);
                break;
            case R.id.login:
                verifySQLite();
                break;
        }

    }

    private void verifySQLite() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
     if (email.isEmpty() || password.isEmpty()) return;
     if (!inputValidation.isEmailValid(email)) {
         Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT);
         toast.show();
     }

        if (databaseHelper.checkUser(email, password)) {
            //successful login
            Intent intent = new Intent(activity, LoggedIn.class);
            startActivity(intent);
        } else {
            //unsuccessful login
            Toast toast = Toast.makeText(getApplicationContext(), "Bad login", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



}
