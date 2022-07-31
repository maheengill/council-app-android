package com.example.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Report {

    private int ID;
    private int issueID;
    private String description;
    private Bitmap image;
    private String location;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIssue() {


        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    private String issue;

    public Report(int issueID) {
        this.issueID = issueID;
    }

    public Report(int ID, String issue, String description, Bitmap image, String location) {
        this.ID = ID;
        this.issue = issue;
        this.description = description;
        this.image = image;
        this.location = location;
    }

    public Report(int ID, String issue, String description, String location) {
        this.ID = ID;
        this.issue = issue;
        this.description = description;
        this.location = location;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
