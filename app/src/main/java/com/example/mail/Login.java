package com.example.mail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //initialising the variables
    EditText etmail,etpass;
   Button btLogin,btRegister;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etmail=findViewById(R.id.editTextTextPersonName4);
        etpass=findViewById(R.id.editTextTextPassword5);
        btLogin=findViewById(R.id.button);
        btRegister=findViewById(R.id.button4);



        //setting on click listener for button
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smail,spass;
                //retrieving the values of email and password of user
                smail=etmail.getText().toString().trim();
                spass=etpass.getText().toString().trim();

                //getting the instance of FirebaseAuth object
                firebaseAuth=FirebaseAuth.getInstance();

                //checking if email and password fields are empty
                if(TextUtils.isEmpty(smail)) {
                    Toast.makeText(Login.this,
                            "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(spass))
                {
                    Toast.makeText(Login.this,
                            "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }



                firebaseAuth.signInWithEmailAndPassword(smail, spass)
                        .addOnCompleteListener(Login.this,
                                new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this,
                                            "Login Successful", Toast.LENGTH_SHORT).show();

                                    Intent i=new Intent(Login.this,options.class);
                                    i.putExtra("sender_mail",smail);
                                    i.putExtra("sender_password",spass);
                                    startActivity(i);

                                }
                                else {
                                    Toast.makeText(Login.this,
                                            "User Not found", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        }

    }

