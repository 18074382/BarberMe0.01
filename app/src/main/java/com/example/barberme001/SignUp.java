package com.example.barberme001;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.model.User;
import com.example.sql.DatabaseHelper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etEmail, etPassword;
    Button button_register;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);

        databaseHelper = new DatabaseHelper(this);


        //create the listener for the register button
        button_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_register) {
            String email = etEmail.getText().toString().trim();
            if (!databaseHelper.checkUser(email)) {
                String userName = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                User user = new User();
                user.setName(userName);
                user.setPassword(password);
                user.setEmail(email);

                databaseHelper.addUser(user);

                //return
                Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Account already exists with that email", Toast.LENGTH_SHORT);
                toast.show();
            }


        }

    }


}


