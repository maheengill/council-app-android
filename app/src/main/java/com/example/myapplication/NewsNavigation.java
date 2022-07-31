package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class NewsNavigation {

    Context context;

    public NewsNavigation(Context context) {
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
                return true;
            case R.id.moreNav:
                context.startActivity(new Intent(context, MoreActivity.class));
                ((Activity)context).overridePendingTransition(0,0);
                return true;
        }
        return false;
    }
}
