package com.example.barberme001;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;



public class HomeScreen extends AppCompatActivity {

    private TextView title;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        title = findViewById(R.id.titleView);

        botNavBar();
    }

    private void botNavBar() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        title.setText("HOME");
                        return true;
                    case R.id.navigation_explore:
                        title.setText("EXPLORE");
                        return true;
                    case R.id.navigation_notifications:
                        title.setText("NOTIFICATIONS");
                        return true;
                    case R.id.navigation_profile:
                        title.setText("PROFILE");
                        return true;
                }
                return false;
            }
        });
    }
}
