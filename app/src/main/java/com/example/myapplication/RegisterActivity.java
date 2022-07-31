package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DBHelper db;
    EditText firstnameTxt, lastNametxt, usernameTxt, passwordTxt, reTypePasswordTxt;
    Button registerBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
        db = new DBHelper(this);

        firstnameTxt = findViewById(R.id.firstName);
        lastNametxt = findViewById(R.id.lastName);
        usernameTxt = findViewById(R.id.username);
        passwordTxt = findViewById(R.id.password);
        reTypePasswordTxt = findViewById(R.id.reTypePassword);
        registerBtn = findViewById(R.id.registerButton);
        loginBtn = findViewById(R.id.signIn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname = firstnameTxt.getText().toString();
                String lastname = lastNametxt.getText().toString();
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                String reTypePassword = reTypePasswordTxt.getText().toString();

                if(username.equals("") || password.equals("") || reTypePassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fields can not be left empty", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6 || reTypePassword.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Passwords must be at least six characters", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.equals(reTypePassword)){
                        Boolean userExists = db.checkUserExists(username);
                        if (userExists == true){
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Boolean userCreated = db.newUser(firstname, lastname, username, password);
                            if (userCreated == true){
                                Toast.makeText(RegisterActivity.this, "User account created", Toast.LENGTH_SHORT).show();
                                SaveSharedPreference.setUserName(RegisterActivity.this, username);
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Account could not be created", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password fields do not match", Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}