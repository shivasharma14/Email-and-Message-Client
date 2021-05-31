package com.example.mail;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<UserHelper,myadapter.myviewholder>
    {
        public myadapter(@NonNull FirebaseRecyclerOptions<UserHelper> options) {
            super(options);
        }
        @Override
        protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserHelper user)
        {
            holder.rm.setText(user.getReceiver());
            holder.sub.setText(user.getSubject());
            holder.bod.setText(user.getMessage());
        }
        @NonNull
        @Override
        public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_singlerow,
                    parent,false);
            return new myviewholder(view);
        }
        class myviewholder extends RecyclerView.ViewHolder
        {
            TextView rm,sub,bod;
            public myviewholder(@NonNull View itemView)
            {
                super(itemView);
                rm=(TextView)itemView.findViewById(R.id.EName);
                sub=(TextView)itemView.findViewById(R.id.ESubject);
                bod=(TextView)itemView.findViewById(R.id.EBody);
            }
        }
    }

