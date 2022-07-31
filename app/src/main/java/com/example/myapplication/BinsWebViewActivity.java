package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BinsWebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bins_web_view);
        setTitle("Bins");

        // load web view
        webView = findViewById(R.id.bins_web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.kirklees.gov.uk/beta/your-property-bins-recycling/your-bins/?transaction=bin-collection-dates");

        // select bottom navigation item
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.homeNav);
        // bottom navigation click
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MainNavigation nav = new MainNavigation(BinsWebViewActivity.this);
                return nav.navigation(menuItem);
            }
        });
    }
}