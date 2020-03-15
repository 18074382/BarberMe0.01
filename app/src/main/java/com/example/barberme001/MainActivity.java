package com.example.barberme001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.helpers.InputValidation;
import com.example.model.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = "EmailPassword";

    private InputValidation inputValidation;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editText1);
        editTextPassword = findViewById(R.id.editText2);

        blogin = findViewById(R.id.login);
        bregister = findViewById(R.id.register);

        inputValidation = new InputValidation(this);


        blogin.setOnClickListener(this);
        bregister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Intent intent = new Intent(activity, SignUp.class);
                startActivity(intent);
                break;
            case R.id.login:
                login();
                break;
        }

    }
    private void login(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) return;
        if (!inputValidation.isEmailValid(email)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (!email.isEmpty() && !password.isEmpty() && new InputValidation(this).isEmailValid(email)) {
            //successful login
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
            Intent intent = new Intent(activity, HomeScreen.class);
            startActivity(intent);
            finish();
        } else {
            //unsuccessful login
            Toast toast = Toast.makeText(getApplicationContext(), "Bad login", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




}
