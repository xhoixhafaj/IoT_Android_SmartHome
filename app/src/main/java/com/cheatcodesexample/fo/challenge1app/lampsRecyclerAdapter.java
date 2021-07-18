package com.cheatcodesexample.fo.challenge1app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class lampsRecyclerAdapter extends RecyclerView.Adapter<lampsRecyclerAdapter.ViewHolder> {

    private ArrayList<lamps> Lamps=new ArrayList<>();
    private Context context;
    private DatabaseReference mDatabase;
    private DatabaseReference mDBase;

    public lampsRecyclerAdapter(Context context) {
        this.context = context;
    }

    public lampsRecyclerAdapter() {
    }

    @Override
    public int getItemCount() {
        return Lamps.size();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lamps_list_layout,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtLampsLayout.setText(Lamps.get(position).getName());

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Led");
        mDBase=FirebaseDatabase.getInstance().getReference().child("LedNames");
        if (Lamps.get(position).getValue().equals("on")){
            holder.switchLampsLayout.setChecked(true);
            holder.imageLampsLayout.setBackgroundResource(R.drawable.lampturnedon);
        } else {
            holder.switchLampsLayout.setChecked(false);
            holder.imageLampsLayout.setBackgroundResource(R.drawable.lampturnedoff);
        }
        holder.lampsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, Lamps.get(position).getName()+"   selected", Toast.LENGTH_SHORT).show();
            }
        });

        holder.switchLampsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.switchLampsLayout.isChecked()){
                    holder.imageLampsLayout.setBackgroundResource(R.drawable.lampturnedon);
                    mDatabase.child(Lamps.get(position).getPinNames()).setValue("on");
                    Lamps.get(position).setValue("on");
                } else {
                    holder.imageLampsLayout.setBackgroundResource(R.drawable.lampturnedoff);
                    mDatabase.child(Lamps.get(position).getPinNames()).setValue("off");
                    Lamps.get(position).setValue("off");
                }

            }
        });


    }

    public void setLamps(ArrayList<lamps> lamps) {
        this.Lamps = lamps;
        notifyDataSetChanged();
    }

    public void  clearAdapterData(){
        this.Lamps.clear();
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtLampsLayout;
        private CardView lampsLayout;
        private ImageView imageLampsLayout;
        private Switch switchLampsLayout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtLampsLayout=itemView.findViewById(R.id.txtLampsLayout);
            lampsLayout=itemView.findViewById(R.id.lampsLayout);
            switchLampsLayout=itemView.findViewById(R.id.switchLampsLayout);
            imageLampsLayout=itemView.findViewById(R.id.imageLampsLayout);


        }
    }
}
