package com.example.mail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    //initialising the variables
    EditText etmail,etpass;
   Button btLogin;
    String smail,spass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etmail=findViewById(R.id.editTextTextPersonName2);
        etpass=findViewById(R.id.editTextTextPassword2);
        btLogin=findViewById(R.id.button);
        //retrieving the values of email and password of user
        smail=etmail.getText().toString();
        spass=etpass.getText().toString();

        //setting on click listener for button
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        





        }



    }

