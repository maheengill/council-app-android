package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactsActivity extends AppCompatActivity {

    ImageView facebookImg, twitterImg, instagramImg;
    TextView phoneTxt, facebookTxt, twitterTxt, instagramTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setTitle("Social Media");


        facebookImg = findViewById(R.id.myReportsImg);
        twitterImg = findViewById(R.id.twitterImg);
        instagramImg = findViewById(R.id.socialsImg);
        phoneTxt = findViewById(R.id.userDetails);
        facebookTxt = findViewById(R.id.myReports);
        twitterTxt = findViewById(R.id.twitter);
        instagramTxt = findViewById(R.id.socials);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.moreNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MoreNavigation nav = new MoreNavigation(ContactsActivity.this);
                return nav.navigation(menuItem);
            }
        });
    }

    public void clickFacebook(View v){
        String appLink = "fb://page/liveinkirklees";
        String linkPackage = "com.facebook.katana";
        String webLink = "https://www.facebook.com/liveinkirklees/";
        openLink(appLink, linkPackage, webLink);
    }

    public void clickTwitter(View v){
        String appLink = "twitter://user?screen_name=KirkleesCouncil";
        String linkPackage = "com.twitter.android";
        String webLink = "https://twitter.com/KirkleesCouncil";
        openLink(appLink, linkPackage, webLink);

    }

    public void clickInstagram(View v) {
        String appLink = "http://www.instagram.com/u/kirklees.council/";
        String linkPackage = "com.instagram.android";
        String webLink = "https://www.instagram.com/kirklees.council/";
        openLink(appLink, linkPackage, webLink);
    }

    private void openLink(String appLink, String linkPackage, String webLink) {
        try{
            Uri uri = Uri.parse(appLink);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(linkPackage);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        catch (Exception e){
            Uri uri = Uri.parse(webLink);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

}