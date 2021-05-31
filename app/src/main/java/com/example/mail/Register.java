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

public class Register extends AppCompatActivity {
    //initialising the variables
    EditText etmail1,etpass1,etcpass;
    Button btRegister1;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etmail1=findViewById(R.id.person_email);
        etpass1=findViewById(R.id.person_pass1);
        etcpass=findViewById(R.id.person_pass2);
        btRegister1=findViewById(R.id.button_reg);

        //setting on click listener for button
        btRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sepass,semail,cpass;

                //retrieving the values of email and password of user
                semail=etmail1.getText().toString();
                sepass=etpass1.getText().toString();
                cpass=etcpass.getText().toString();

                //getting the instance of FirebaseAuth object
                firebaseAuth=FirebaseAuth.getInstance();
                //checking if email and password fields are empty

                if(TextUtils.isEmpty(semail)) {
                    Toast.makeText(Register.this,
                            "Please enter Email", Toast.LENGTH_SHORT).show();
                        return;
                }
                if(TextUtils.isEmpty(sepass))
                {
                    Toast.makeText(Register.this,
                            "Please enter Password", Toast.LENGTH_SHORT).show();
                        return;
                }
               if(TextUtils.isEmpty(cpass))
                {
                    Toast.makeText(Register.this,
                            "Please enter Confirm Password", Toast.LENGTH_SHORT).show();
                            return;
                }
                if(sepass.length()<8)
                {
                    Toast.makeText(Register.this,
                            "Password must be at least 8 characters long",
                            Toast.LENGTH_LONG).show();
                }
                if(!sepass.equals(cpass))
                {
                    Toast.makeText(Register.this,
                            "Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }



                //if password and confirm password match
               if(sepass.equals(cpass))
               {

                   firebaseAuth.createUserWithEmailAndPassword(semail, sepass)
                           .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       Toast.makeText(Register.this,
                                               "Registration Successful", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(getApplicationContext(),Login.class));


                                   }
                                   else {
                                       Toast.makeText(Register.this,
                                               "Registration Failed", Toast.LENGTH_SHORT).show();

                                   }


                               }
                           });
               }
            }
        });
    }
}
