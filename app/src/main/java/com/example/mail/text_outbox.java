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

public class text_outbox extends AppCompatActivity {
    RecyclerView recyclerView;
    myadapter1 adapter;
    String s1,s2;
    Button b1new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_outbox);
        Intent i=getIntent();
        s1=i.getStringExtra("s_email");
        s2=i.getStringExtra("s_pass");
        String []se=s1.split("@");

        recyclerView=(RecyclerView)findViewById(R.id.r1view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<UserHelper> options =
                new FirebaseRecyclerOptions.Builder<UserHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Text").child(se[0]),
                                UserHelper.class)
                        .build();

        adapter=new myadapter1(options);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));




        b1new=findViewById(R.id.button3);
        b1new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sec2=new Intent(text_outbox.this,message.class);
                sec2.putExtra("s_email",s1);
                sec2.putExtra("s_pass",s2);
                startActivity(sec2);
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