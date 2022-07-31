package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.NewsModels.NewsAPIResponse;
import com.example.myapplication.NewsModels.NewsArticle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class NewsActivity extends AppCompatActivity{

    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.recyler_news);
        setTitle("Local News");

        NewsAPIRequestManager newsManager = new NewsAPIRequestManager(this);
        newsManager.getNewsArticles(listener, "general", null);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.newsNav);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Retrieving news articles...");
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();//dismiss dialog
            }
        });
        progressDialog.show();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                NewsNavigation nav = new NewsNavigation(NewsActivity.this);
                return nav.navigation(menuItem);
            }
        });
    }

    private final OnFetchDataListener<NewsAPIResponse> listener = new OnFetchDataListener<NewsAPIResponse>() {
        @Override
        public void onFetchData(List<NewsArticle> articles, String msg) {
            progressDialog.dismiss();
            displayNews(articles);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(NewsActivity.this, "Response not successful", Toast.LENGTH_LONG);
        }
    };

    private void displayNews(List<NewsArticle> articles) {
        recyclerView = findViewById(R.id.recyler_news);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        newsAdapter = new NewsAdapter(this, articles);
        recyclerView.setAdapter(newsAdapter);
    }

}