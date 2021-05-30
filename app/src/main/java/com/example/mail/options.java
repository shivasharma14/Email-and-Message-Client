package com.example.mail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class options extends AppCompatActivity {
    Button sendmail,sendmsg,log_out;
    String sm,sg; //for email and password


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        sendmail=findViewById(R.id.send_mail);
        sendmsg=findViewById(R.id.send_msg);
        log_out=findViewById(R.id.logout);
        Intent in=getIntent();
        sm=in.getStringExtra("sender_mail");
        sg=in.getStringExtra("sender_password");
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sec=new Intent(options.this,MainActivity.class);
                sec.putExtra("s_email",sm);
                sec.putExtra("s_pass",sg);
                startActivity(sec);
            }
        });
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sec1=new Intent(options.this,message.class) ;
                startActivity(sec1);
            }
        });
        log_out .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent sec2=new Intent(options.this,Login.class);
              sec2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(sec2);

            }
        });


    }
}