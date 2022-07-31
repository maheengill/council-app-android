package com.example.myapplication;

import static org.junit.jupiter.api.Assertions.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.content.ContextCompat;
import androidx.test.core.app.ApplicationProvider;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


@RunWith(RobolectricTestRunner.class)
class ReportTest {

    // checks the first constructor sets the description of the report correctly
    @Test
    void checkDescriptionConstructor1() {
        String description = "description";
        Report report = new Report(1, "issue", description, "location");
        assertEquals(description, report.getDescription());
    }

    // checks the first constructor sets the location of the report correctly
    @Test
    void checkLocationConstructor1() {
        String location = "location";
        Report report = new Report(1, "issue", "description", location);
        assertEquals(location, report.getLocation());
    }

    // checks the second constructor sets the issue of the report correctly
    @Test
    void checkIssueConstructor1() {
        String issue = "issue";
        Report report = new Report(1, issue, "description", "location");
        assertEquals(issue, report.getIssue());
    }


}