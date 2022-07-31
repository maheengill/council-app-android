package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;
    EditText usernameTxt, passwordTxt;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        db = new DBHelper(this);


        usernameTxt = (EditText) findViewById(R.id.login_username);
        passwordTxt = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login_signIn);
        registerBtn = (Button) findViewById(R.id.login_registerButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean auth = db.authenticate(username, password);
                    if (auth == true){
                        Toast.makeText(LoginActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setUserName(LoginActivity.this, username);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}