package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SensorsPage extends AppCompatActivity {

    private RecyclerView sensorsRecyclerView1;
    private DatabaseReference mDBase;
    private DatabaseReference mDataBase;
    private int i;
    private Button goToLampsButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_page);

        sensorsRecyclerView1=findViewById(R.id.sensorsRecyclerView1);
        goToLampsButt=findViewById(R.id.goToLampsButt);

        ArrayList<Sensors> sensors=new ArrayList<Sensors>();
        ArrayList<String> typeOfSensors=new ArrayList<>();
        //
        //

        SensorRecyclerAdapter adapter= new SensorRecyclerAdapter(this);
        adapter.setSensors(sensors);

        sensorsRecyclerView1.setAdapter(adapter);
        sensorsRecyclerView1.setLayoutManager(new GridLayoutManager(this,1));

        System.out.println("adapteri eshte krijuar");

        mDBase= FirebaseDatabase.getInstance().getReference().child("tipiSensorit");
        i=0;

        mDBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()
                ) {
                    sensors.add(new Sensors(s.getValue().toString(),s.getKey()));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        mDataBase= FirebaseDatabase.getInstance().getReference().child("Sensors").child("Pin17");
        if (sensors.isEmpty()){
            Toast.makeText(this, "Waiting for response", Toast.LENGTH_SHORT).show();
        } else {
            mDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot s : snapshot.getChildren()
                    ) {
                        System.out.println(s);
                        System.out.println(s.getKey());
                        System.out.println(s.getValue().toString());
                        if (s.getKey().toString().equals("0")) {
                            sensors.get(i).setValue1(s.getValue().toString());
                            System.out.println(s.getValue().toString());
                        } else if (s.getKey().toString().equals("1")) {
                            sensors.get(i).setValue2(s.getValue().toString());
                            System.out.println(s.getValue().toString());
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }



        goToLampsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clearAdapterData();
                OpenActivityLamps();

            }
        });


    }

    private void OpenActivityLamps(){
        Intent i=new Intent(this,firstActivity.class);
        startActivity(i);
        finish();
    }


}