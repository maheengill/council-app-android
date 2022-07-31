package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDetailsActivity extends AppCompatActivity {

    User user;
    DBHelper dbHelper = new DBHelper(this);
    EditText firstName, lastName;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        setTitle("My Details");

        user = dbHelper.getUser();

        firstName = findViewById(R.id.firstNameInput);
        lastName = findViewById(R.id.lastNameInput);
        button = findViewById(R.id.submitUser);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.moreNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                MoreNavigation nav = new MoreNavigation(UserDetailsActivity.this);
                return nav.navigation(menuItem);
            }
        });

    }

    public void updateUserDetails(View view){
        boolean success = dbHelper.updateUser(
                dbHelper.getUser().getUsername(),
                firstName.getText().toString(),
                lastName.getText().toString());
        if (success)
            Toast.makeText(this, "Details updated!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Details couldn't be updated. Please try again", Toast.LENGTH_SHORT).show();
    }

}