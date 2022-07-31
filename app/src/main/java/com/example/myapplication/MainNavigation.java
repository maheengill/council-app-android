package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class MainNavigation
{
    Context context;

    public MainNavigation(Context context) {
        this.context = context;
    }

    public boolean navigation(@NonNull MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.homeNav:
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
                context.startActivity(new Intent(context, MoreActivity.class));
                ((Activity)context).overridePendingTransition(0,0);
                return true;
        }
        return false;
    }
}
