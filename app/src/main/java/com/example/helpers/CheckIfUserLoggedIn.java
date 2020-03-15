package com.example.helpers;

import androidx.appcompat.app.AppCompatActivity;


import com.example.barberme001.HomeScreen;
import com.example.barberme001.MainActivity;
import com.example.barberme001.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;

public class CheckIfUserLoggedIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            //there is some user logged in
        }else{
            //no one logged in
            startActivity(new Intent(this, HomeScreen.class));
            finish();
        }

    }
}
