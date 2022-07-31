package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ReportActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button buttonSubmit;
    ImageView locationImageView;
    EditText editDescription;
    TextView locationTxt;
    Bitmap image;
    String issue;
    int issueID;
    Uri imageFilePath;
    ImageView reportImage, selectedImage;
    Report report;
    String location;
    int MAPS_REQUEST_CODE = 50;
    int CAMERA_REQUEST_CODE = 200;
    int GALLERY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Report");

        Intent i = getIntent();
        issueID = i.getIntExtra("issueId", 0);
        report = new Report(issueID);
        issue = i.getStringExtra("issue");
        TextView tv = findViewById(R.id.issueNameInReport);
        tv.setText(issue);

        editDescription = findViewById(R.id.editDescription);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        dbHelper = new DBHelper(ReportActivity.this);
        reportImage = findViewById(R.id.reportImage);
        selectedImage = findViewById(R.id.selectedImage);
        locationImageView = findViewById(R.id.locationImageView);
        locationTxt = findViewById(R.id.locationTextView);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (report.getLocation() == null){
                    Toast.makeText(ReportActivity.this, "Please enter the location of the issue",Toast.LENGTH_LONG).show();
                }
                else if (editDescription.getText().toString().isEmpty()){
                    Toast.makeText(ReportActivity.this, "Please enter a description of the issue", Toast.LENGTH_LONG).show();
                }
                else {
                    report.setDescription(editDescription.getText().toString());
                    boolean success = dbHelper.newReport(report);
                    if (success == true) {
                        Toast.makeText(ReportActivity.this, "Success", Toast.LENGTH_LONG).show();
                        Intent openReportIntent = new Intent(ReportActivity.this, MyReportsActivity.class);
                        startActivity(openReportIntent);
                    }
                    else
                        Toast.makeText(ReportActivity.this, "Success", Toast.LENGTH_LONG).show();
                }
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.reportNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                ReportItNavigation nav = new ReportItNavigation(ReportActivity.this);
                return nav.navigation(menuItem);
            }
        });

    }

    public void selectLocation(View view){
        Intent i = new Intent(this, MapsActivityCurrentPlace.class);
        startActivityForResult(i, MAPS_REQUEST_CODE);
    }


    public void selectImage(View view){
        final CharSequence[] choose = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(ReportActivity.this);
        dialog.setTitle("Choose image from:");


        dialog.setItems(choose, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choose[i].equals("Camera")){
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

                }
                else if (choose[i].equals("Gallery")){
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, GALLERY_REQUEST_CODE);
                }
                else{
                    dialogInterface.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (resultCode == Activity.RESULT_OK) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == GALLERY_REQUEST_CODE && data.getData() != null) {
                    imageFilePath = data.getData();
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                    report.setImage(image);
                    selectedImage.setImageBitmap(Bitmap.createBitmap(image));
                    selectedImage.setVisibility(View.VISIBLE);

                } else if (requestCode == CAMERA_REQUEST_CODE) {
                    Bundle bundle = data.getExtras();
                    report.setImage((Bitmap) bundle.get("data"));
                    selectedImage.setImageBitmap((Bitmap) bundle.get("data"));
                    selectedImage.setVisibility(View.VISIBLE);
                } else if (requestCode == MAPS_REQUEST_CODE) {
                    location = data.getStringExtra("location");
                    report.setLocation(location);
                    locationTxt.setText("Location selected.");
                }
            }
        }
        catch (Exception exception)
        {
        }
    }
}