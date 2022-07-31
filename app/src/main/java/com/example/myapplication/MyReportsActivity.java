package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MyReportsActivity extends AppCompatActivity {

    DBHelper db;
    RecyclerView recyclerView;
    private ArrayList<Report> reports;
    TextView noReportsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        setTitle("My Reports");

        db = new DBHelper(this);
        reports = db.getReports();
        noReportsText = findViewById(R.id.noReports);

        if (!reports.isEmpty()){
            noReportsText.setVisibility(View.GONE);
        }

        recyclerView = findViewById(R.id.reportsRecyclerVIew);
        ReportsAdapter adapter = new ReportsAdapter(reports, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.moreNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MoreNavigation nav = new MoreNavigation(MyReportsActivity.this);
                return nav.navigation(menuItem);
            }
        });

    }
}