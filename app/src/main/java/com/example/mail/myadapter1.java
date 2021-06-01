package com.example.mail;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter1 extends FirebaseRecyclerAdapter<UserHelper,myadapter1.myviewholder>
{
    public myadapter1(@NonNull FirebaseRecyclerOptions<UserHelper> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserHelper user)
    {
        holder.num.setText(user.getReceiver());

        holder.ms.setText(user.getMessage());
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_singlerow1,
                parent,false);
        return new myviewholder(view);
    }
    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView num,ms;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            num=(TextView)itemView.findViewById(R.id.ENumber);

            ms=(TextView)itemView.findViewById(R.id.EMsg);
        }
    }
}

