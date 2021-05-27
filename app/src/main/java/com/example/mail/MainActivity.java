package com.example.mail;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MainActivity extends AppCompatActivity {
    //initialising the variables
    EditText etTo,etSubject,etBody;
    Button btSend;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning the variables
        etTo=findViewById(R.id.et_to);
        etSubject=findViewById(R.id.et_subject);
        etBody=findViewById(R.id.et_body);
        btSend=findViewById(R.id.bt_send);

        //Sender's credentials
        email="sharmashiva100s@gmail.com";
        password="Ssquare100%";

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialise properties
                Properties properties=new Properties();
                /*properties.put("mail.smtp.auth","true");
                 properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("smtp.port","587");
                */

                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");
                //initialise session variables
                Session session=Session.getInstance(properties, new Authenticator() {
                    @Override
                    //password authentication
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email,password);
                    }
                });



                try {
                    //Initialize email content
                    MimeMessage message=new MimeMessage(session);

                    //Sender email
                    message.setFrom(new InternetAddress(email));

                    //Recipient email
                    message.addRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(etTo.getText().toString().trim()));

                    //Email Subject
                    message.setSubject(etSubject.getText().toString().trim());

                    //Email message
                    message.setText(etBody.getText().toString().trim());

                    //Send email
                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    private class SendMail extends AsyncTask<Message,String,String> {
        //Initialize a progress dialog
        private ProgressDialog progressDialog;
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            //create and show progress dialog
            progressDialog=ProgressDialog.show(MainActivity.this,
                    "Please Wait","Sending Mail...",
                    true,
                    false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //When successful
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                //When error
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss progress dialog
            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                //When Success

                //Initialize alert dialog
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail Sent Successfully!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Clear all the edit text fields
                        etTo.setText("");
                        etSubject.setText("");
                        etBody.setText("");

                    }
                });
                //Show alert dialog
                builder.show();
            }
            else{
                //When error
                Toast.makeText(getApplicationContext(),
                        "Something went wrong?",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}