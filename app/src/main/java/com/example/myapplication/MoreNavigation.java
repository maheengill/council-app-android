package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class MoreNavigation {

    Context context;

    public MoreNavigation(Context context) {
        this.context = context;
    }

    public boolean navigation(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.homeNav:
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity)context).overridePendingTransition(0,0);
                return true;
            case R.id.reportNav:
                context.startActivity(new Intent(context, IssuesActivity.class));
                ((Activity)context).overridePendingTransition(0,0);
                return true;
            case R.id.newsNav:
                context.startActivity(new Intent(context, NewsActivity.class));
                ((Activity)context).overridePendingTransition(0,0);
                return true;
            case R.id.moreNav:
                return true;
        }
        return false;
    }
}
