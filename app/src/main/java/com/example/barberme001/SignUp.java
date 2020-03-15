package com.example.barberme001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.helpers.InputValidation;
import com.example.model.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etEmail, etPassword;
    Button button_register;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etPassword = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);

        mAuth = FirebaseAuth.getInstance();



        //create the listener for the register button
        button_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_register) {
            String email = etEmail.getText().toString().trim();
            String username = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please enter values", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if (!new InputValidation(this).isEmailValid(email)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if (!email.isEmpty() && !username.isEmpty() && !password.isEmpty() && new InputValidation(this).isEmailValid(email)) {

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

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


