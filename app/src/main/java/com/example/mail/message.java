package com.example.mail;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class message extends AppCompatActivity {
    EditText et1,et2;
    Button bt1;
    String em;
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();;
    DatabaseReference reference, reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        et1=findViewById(R.id.et_phone_num);
        et2=findViewById(R.id.et_text);
        bt1=findViewById(R.id.bt_send);
        Intent i=getIntent();
        em=i.getStringExtra("s_email");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        sendSMS();

                    }
                    else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });

    }
    private void sendSMS()
    {
        String phone=et1.getText().toString().trim();
        String msg=et2.getText().toString().trim();
        try {
            if(phone.length()!=10)
            {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
            }
            else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, msg, null, null);
                Toast.makeText(this,
                        "Message Sent", Toast.LENGTH_LONG).show();
                String []se=em.split("@");
                String r = et1.getText().toString();
                String m = et2.getText().toString();

                reference = rootNode.getReference("Text" );
                DatabaseReference mailRef = reference.child(se[0]);
                String key = mailRef.push().getKey(); //generating a unique key
                UserHelper user = new UserHelper(r,m);
                mailRef.child(key).setValue(user); //setting the object node
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this,
                    "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }




}