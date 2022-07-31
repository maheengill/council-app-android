package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        setTitle("More");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.moreNav);
        
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MoreNavigation nav = new MoreNavigation(MoreActivity.this);
                return nav.navigation(menuItem);
            }
        });
    }

    public void clickPhone (View v){
        String number = "+447548563326";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }


    public void openUserDetails(View v){
        // launch the my reports activity

        Intent i = new Intent(this, UserDetailsActivity.class);
        startActivity(i);
    }

    public void getReports(View v){
        // launch the my reports activity

        Intent i = new Intent(this, MyReportsActivity.class);
        startActivity(i);
    }

    public void getSocials(View v){
        // launch the my reports activity

        Intent i = new Intent(this, ContactsActivity.class);
        startActivity(i);
    }


}