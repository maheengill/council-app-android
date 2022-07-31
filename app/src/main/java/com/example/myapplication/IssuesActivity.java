package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class IssuesActivity extends AppCompatActivity {

    DBHelper db;
    RecyclerView recyclerView;
    private ArrayList<String> issuesList;
    private ArrayList<Integer> issuesIDsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        setTitle("Issues");

        issuesList = new ArrayList<>();
        issuesIDsList = new ArrayList<>();
        db = new DBHelper(IssuesActivity.this);
        storeIssuesToArrays();

        recyclerView = findViewById(R.id.issuesRecyclerView);
        setAdapter();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.reportNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                ReportItNavigation nav = new ReportItNavigation(IssuesActivity.this);
                return nav.navigation(menuItem);
            }
        });

    }

    private void storeIssuesToArrays() {
        Cursor cursor = db.getIssues();
        cursor.moveToFirst();
        while (cursor.getCount() != 0 && !cursor.isAfterLast()){
            issuesIDsList.add(cursor.getInt(0));
            issuesList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(issuesList, issuesIDsList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}