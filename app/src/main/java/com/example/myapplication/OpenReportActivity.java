package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.NewsModels.NewsArticle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OpenReportActivity extends AppCompatActivity {

    Report report;
    TextView issue, description, location;
    ImageView imageView;
    DBHelper db;
    private ArrayList<Report> reports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_report);
        setTitle("My Report");

        db = new DBHelper(this);
        reports = db.getReports();

        issue = findViewById(R.id.open_report_issue);
        description = findViewById(R.id.open_report_description);
        location = findViewById(R.id.open_report_location);
        imageView = findViewById(R.id.open_report_image);


        issue.setText(getIntent().getStringExtra("issue"));
        description.setText("DESCRIPTION:\n"+getIntent().getStringExtra("description"));
        location.setText("LOCATION:\n"+getIntent().getStringExtra("location"));

        int id = getIntent().getIntExtra("ID",0);

        if(reports.get(id).getImage() != null) {
            imageView.setImageBitmap(reports.get(id).getImage());
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.moreNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav:
                        startActivity(new Intent(OpenReportActivity.this, ContactsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.reportNav:
                        startActivity(new Intent(OpenReportActivity.this, IssuesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.newsNav:
                        startActivity(new Intent(OpenReportActivity.this, NewsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.moreNav:
                        return true;
                }
                return false;
            }
        });
    }
}