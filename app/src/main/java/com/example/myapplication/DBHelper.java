package com.example.myapplication;

import static java.lang.String.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    ByteArrayOutputStream imageArray;
    byte[] imageBytes;

    public DBHelper(@Nullable Context context) {
        super(context, "myAppDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createIssuesTablesStatement = "CREATE TABLE Issues (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT);";
        String createReportsTablesStatement = "CREATE TABLE Reports(ID INTEGER PRIMARY KEY AUTOINCREMENT, Description TEXT," +
                "  IssueID INTEGER, Image BLOB, Location TEXT, FOREIGN KEY (IssueID) REFERENCES ISSUE(ID));";
        String InsertIssues = "INSERT INTO Issues (Name) VALUES ('Graffiti'), ('Pothole'), ('Flooding or Drainage'), ('Faulty Streetlight'), ('Obstruction'), ('Road markings'), ('Other');";
        String CreateUsersTable = "CREATE TABLE Users (firstName TEXT, lastName TEXT, Username TEXT PRIMARY KEY, Password TEXT)";
        // String CreateAttractionsTable = "CREATE TABLE Attractions (Name TEXT, Description TEXT, Location TEXT PRIMARY KEY, Password TEXT)";
        String createRecyclePointsTable = "CREATE TABLE RecyclePoints (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Address TEXT, Latitude TEXT, Longitude TEXT)";
        String InsertRecyclePoints = "INSERT INTO RecyclePoints (Name, Address) VALUES " +
                "('Emerald Street', 'Emerald Street (off Hillhouse Lane)\n" +
                "Huddersfield\n" +
                "HD1 6BY')," +
                "('Bent Ley Road', 'Bent Ley Road\n" +
                "Meltham\n" +
                "Huddersfield\n" +
                "HD9 4AP')," +
                "('Bromley Farm', 'Bromley Farm\n" +
                "Upper Cumberworth (off Barnsley Road)\n" +
                "HD8 8NN')," +
                "('Weaving Lane','Weaving Lane (off Thornhill Road)\n" +
                "Dewsbury\n" +
                "WF12 9QR'), " +
                "('Nab Lane', 'Nab Lane (off Pheasant Drive)\n" +
                "Birstall\n" +
                "WF17 9LR')";


        db.execSQL(createIssuesTablesStatement);
        db.execSQL(createReportsTablesStatement);
        db.execSQL(InsertIssues);
        db.execSQL(CreateUsersTable);
        db.execSQL(createRecyclePointsTable);
        db.execSQL(InsertRecyclePoints);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Issues");
        db.execSQL("DROP TABLE IF EXISTS Reports");
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public boolean newReport(Report report){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Description", report.getDescription().toString());
        cv.put("IssueID", report.getIssueID());

        // if report to be created has an image
        if (report.getImage() != null) {
            imageArray = new ByteArrayOutputStream();
            report.getImage().compress(Bitmap.CompressFormat.JPEG, 100, imageArray);
            imageBytes = imageArray.toByteArray();
            cv.put("Image", imageBytes);
        }

        cv.put("location", report.getLocation());


        long insert = db.insert("Reports", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public ArrayList<Report> getReports() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Report> reports = new ArrayList<Report>();


        Cursor cursor = db.rawQuery("SELECT * FROM Reports", null);
        cursor.moveToFirst();

        // while cursor items
        while (cursor.getCount() != 0 && !cursor.isAfterLast()) {
            Cursor cursorIssues =db.rawQuery("SELECT Name FROM Issues WHERE ID="+cursor.getInt(2), null);
            cursorIssues.moveToFirst();
            String issue = cursorIssues.getString(0);
            // if report has an image
            if(cursor.getBlob(3) != null ) {
                reports.add(new Report(cursor.getInt(0),
                        issue,
                        cursor.getString(1),
                        BitmapFactory.decodeByteArray(cursor.getBlob(3), 0, cursor.getBlob(3).length),
                        cursor.getString(4)));
                cursor.moveToNext();
            }
            else {
                // if report doesn't have an image
                reports.add(new Report(cursor.getInt(0),
                        issue,
                        cursor.getString(1),
                        cursor.getString(4)));
                cursor.moveToNext();
            }
        }


        cursor.close();
        return reports;
    }

    public Cursor getIssues(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Issues", null);
        return cursor;
    }

    public boolean newUser(String firstName, String lastName, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("firstName", firstName);
        cv.put("lastName", lastName);
        cv.put("Username", username);
        cv.put("Password", password);

        long insert = db.insert("Users", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public boolean checkUserExists(String username){
        SQLiteDatabase db = getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Username=?", new String[]{username});

        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean authenticate (String username, String password){
        SQLiteDatabase db = getWritableDatabase();


        Cursor cursor = db.rawQuery(
                "SELECT * FROM Users WHERE Username=? AND Password=?",
                new String[]{username, password});

        if(cursor.getCount() > 0) return true;
        else return false;
    }

    User getUser(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Users",null);

        cursor.moveToFirst();
        User user = new User(cursor.getString(0),cursor.getString(1), cursor.getString(2));


        return user;
    }


    public Boolean updateUser(String username, String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("firstName", firstName);
        cv.put("lastName", lastName);

        long insert = db.update("Users", cv, "Username=?", new String[]{username});

        if (insert == -1) return false;
        else return true;
    }

    public ArrayList<String> getRecyclePoints() {
        ArrayList<String> recyclePoints = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM RecyclePoints", null);
        cursor.moveToFirst();

        while (cursor.getCount() != 0 && !cursor.isAfterLast()) {
            recyclePoints.add(cursor.getString(2));
            cursor.moveToNext();
        }

        cursor.close();
        return recyclePoints;
    }
}



