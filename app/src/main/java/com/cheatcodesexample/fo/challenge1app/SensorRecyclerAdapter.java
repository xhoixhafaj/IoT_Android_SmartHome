package com.cheatcodesexample.fo.challenge1app;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SensorRecyclerAdapter extends RecyclerView.Adapter<SensorRecyclerAdapter.ViewHolder1> {
    private ArrayList<com.cheatcodesexample.fo.challenge1app.Sensors> Sensors = new ArrayList<>();
    private Context context;
    private DatabaseReference mData;

    public SensorRecyclerAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensors_list_item, parent, false);
        ViewHolder1 holder= new ViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder1 holder, int position) {
        holder.senstxt1.setText("Temperatura "+Sensors.get(position).getValue2()+"°C");
        holder.sensTxt2.setText("Lageshtia "+Sensors.get(position).getValue1()+"%");
        holder.image.setBackgroundResource(R.drawable.temphumiditylogo1);

        mData= FirebaseDatabase.getInstance().getReference().child("Sensors").child(Sensors.get(position).getPinNames());
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()
                     ) {
                    if(s.getKey().toString().equals("0")){
                        holder.sensTxt2.setText("Lageshtia "+s.getValue().toString()+"%");
                        System.out.println(s.getValue().toString());
                    } else if (s.getKey().toString().equals("1")) {
                        holder.senstxt1.setText("Temperatura "+s.getValue().toString()+"°C");
                        System.out.println(s.getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return Sensors.size();
    }

    public void setSensors(ArrayList<com.cheatcodesexample.fo.challenge1app.Sensors> sensors) {
        this.Sensors = sensors;
        notifyDataSetChanged();
    }

    public void  clearAdapterData(){
        this.Sensors.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        private CardView parentSens1;
        private TextView senstxt1,sensTxt2;
        private ImageView image;
        public ViewHolder1(@NonNull @NotNull View itemView) {
            super(itemView);
            senstxt1=itemView.findViewById(R.id.sensTxtName);
            sensTxt2=itemView.findViewById(R.id.sensTxT1);
            image=itemView.findViewById(R.id.sensImage);
        }
    }
}
