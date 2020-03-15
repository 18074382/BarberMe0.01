package com.example.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseData {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public FirebaseData() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    public boolean loggedIn() {
        if (user != null) return true;
        return false;
    }

    public String getName() {
        return user.getDisplayName();
    }

    public String getEmail() {
        return user.getEmail();
    }

}
