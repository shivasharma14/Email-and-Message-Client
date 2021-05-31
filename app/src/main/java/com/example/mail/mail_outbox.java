package com.example.mail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class mail_outbox extends AppCompatActivity {
    RecyclerView recyclerView;
    myadapter adapter;
    String s1,s2;
    Button bnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_outbox);
        Intent i=getIntent();
        s1=i.getStringExtra("s_email");
        s2=i.getStringExtra("s_pass");
        String []se=s1.split("@");

        recyclerView=(RecyclerView)findViewById(R.id.rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<UserHelper> options =
                new FirebaseRecyclerOptions.Builder<UserHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Email").child(se[0]),
                                UserHelper.class)
                        .build();

        adapter=new myadapter(options);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));




        bnew=findViewById(R.id.button2);
    bnew.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent sec=new Intent(mail_outbox.this,MainActivity.class);
            sec.putExtra("s_email",s1);
            sec.putExtra("s_pass",s2);
            startActivity(sec);
        }
    });




    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}