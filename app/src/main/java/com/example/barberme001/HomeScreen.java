package com.example.barberme001;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.example.helpers.FirebaseData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;



public class HomeScreen extends AppCompatActivity {

    private TextView title;
    private BottomNavigationView bottomNavigationView;
    private FirebaseData fdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initObjects();
        botNavBar();

        //get some data!
        Log.d("testing", "username: " + fdata.getName());
    }

    private void initObjects() {
        fdata = new FirebaseData();
        title = findViewById(R.id.titleView);
    }

    private void botNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        title.setText("HOME");
                        return true;
                    case R.id.navigation_explore:
                        fragment = new ExploreFragment();
                        loadFragment(fragment);
                        title.setText("EXPLORE");
                        return true;
                    case R.id.navigation_notifications:
                        fragment = new InboxFragment();
                        loadFragment(fragment);
                        title.setText("INBOX");
                        return true;
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        title.setText("PROFILE");
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.topcontainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
