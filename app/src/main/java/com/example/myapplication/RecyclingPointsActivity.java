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

public class RecyclingPointsActivity extends AppCompatActivity {

    DBHelper db;
    RecyclerView recyclerView;
    private ArrayList<String> recyclePoints;
    TextView noRecycleText, recycleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_points);

        setTitle("Recycling Points");

        db = new DBHelper(this);
        recyclePoints = db.getRecyclePoints();
        noRecycleText = findViewById(R.id.noRecyclePoints);
        recycleText = findViewById(R.id.recyclePoints);



        if (!recyclePoints.isEmpty()){
            noRecycleText.setVisibility(View.GONE);
        }
        else{
            recycleText.setVisibility(View.GONE);
        }

        recyclerView = findViewById(R.id.reportsRecyclerVIew);
        RecycleAdapter adapter = new RecycleAdapter(recyclePoints, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.homeNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MainNavigation nav = new MainNavigation(RecyclingPointsActivity.this);
                return nav.navigation(menuItem);
            }
        });

    }
}