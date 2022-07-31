package com.example.myapplication;


import static org.junit.Assert.assertEquals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReportInstrumentedTest {

    // checks the second constructor sets the description of the report correctly
    @Test
    public void checkDescriptionConstructor2Test() {
        String description = "description";
        Bitmap bitmap= BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(), R.drawable.ic_more);
        Report report = new Report(1, "issue", description, bitmap,"location");
        assertEquals(description, report.getDescription());
    }

    // checks the second constructor sets the location of the report correctly
    @Test
    public void checkLocationConstructor2() {
        String location = "location";

        Bitmap bitmap= BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(), R.drawable.ic_more);
        Report report = new Report(1, "issue", "description",bitmap, location);
        assertEquals(location, report.getLocation());
    }

    // checks the second constructor sets the issue of the report correctly
    @Test
    public void checkIssueConstructor2() {
        String issue = "issue";
        Bitmap bitmap= BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(), R.drawable.ic_more);
        Report report = new Report(1, issue, "description", bitmap, "location");
        assertEquals(issue, report.getIssue());
    }

    // checks the second constructor sets the image of the report correctly
    @Test
    public void checkImageConstructor2() {
        Bitmap bitmap= BitmapFactory.decodeResource(ApplicationProvider.getApplicationContext().getResources(), R.drawable.ic_more);
        Report report = new Report(1,"issue", "description", bitmap, "location");
        assertEquals(bitmap, report.getImage());
    }
}
