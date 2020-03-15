package com.example.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class FirebaseData {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    private static final String TAG = "username";
    public FirebaseData() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
    }

    public boolean loggedIn() {
        if (user != null) return true;
        return false;
    }

    public String getName() {
        final String currentUID = user.getUid();

        Log.d(TAG, "session UID: " + currentUID);
        DocumentReference ref = db.collection("user").document(currentUID);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        Log.d(TAG, "DATA: " + doc.get("username"));
                    }
                }
            }
        });
    return null;
    }

    public String getEmail() {
        return user.getEmail();
    }

}
