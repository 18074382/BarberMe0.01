package com.example.barberme001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.helpers.InputValidation;

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

        initObjects();

    }

    public void initObjects() {
        inputValidation = new InputValidation(this);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editText1);
        editTextPassword = findViewById(R.id.editText2);

        blogin = findViewById(R.id.login);
        bregister = findViewById(R.id.register);

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
                login();
                break;
        }

    }
    private void login()    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (!inputValidation.isEmailValid(email)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (!email.isEmpty() && !password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, move to explore page
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "User " + user.getDisplayName() + " has been signed in.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(activity, HomeScreen.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Something wasn't quite right. Try again!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                //no values
                Toast.makeText(MainActivity.this, "Please enter your details.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }




}
