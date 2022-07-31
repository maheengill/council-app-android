package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.databinding.RecyclePointsListBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request permissions
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        // SaveSharedPreference.setUserName(this, "");

        // Check user login
        if(SaveSharedPreference.getUserName(MainActivity.this) == "")
        {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        }

        // Bottom navigation

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.homeNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MainNavigation nav = new MainNavigation(MainActivity.this);
                return nav.navigation(menuItem);
            }
        });

        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportIssues(View v){
        // launch the report issues activity
        Intent i = new Intent(this, IssuesActivity.class);
        startActivity(i);
    }

    public void getNews(View v){
        // launch the news activity
        Intent i = new Intent(this, NewsActivity.class);
        startActivity(i);
    }

    public void getContacts(View v){
        // launch the contacts activity
        Intent i = new Intent(this, ContactsActivity.class);
        startActivity(i);

    }

    public void getRecyclePoints(View v){
        // launch the recycle points activity
        Intent i = new Intent(this, RecyclingPointsActivity.class);
        startActivity(i);
    }

    public void getBinsWebView(View v){
        // launch the bins activity
        Intent i = new Intent(this, BinsWebViewActivity.class);
        startActivity(i);
    }

}