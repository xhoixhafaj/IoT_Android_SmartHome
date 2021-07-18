package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class firstActivity extends AppCompatActivity {

    private FloatingActionButton logoutButton;
    private FloatingActionButton lampsAddButt;
    private FloatingActionButton lampsDeleteButt;
    private RecyclerView lampsRecyclerView;
    private LinearLayout lampsLinear;
    private DatabaseReference mDatabase;
    private DatabaseReference mDBase;
    private int i;
    private Button goToSensorsButt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        logoutButton=findViewById(R.id.logoutButton);
        lampsAddButt=findViewById(R.id.lampsAddButt);
        lampsDeleteButt=findViewById(R.id.lampsDeleteButt);
        lampsRecyclerView=findViewById(R.id.lampsRecyclerView);
        goToSensorsButt=findViewById(R.id.goToSensorsButt);


        // inicilaze the lamps list as an arraylist
         ArrayList<String> Val=new ArrayList<String>();
         ArrayList<String> NameOfRoom=new ArrayList<String>();
         ArrayList<String> NameOfPin=new ArrayList<String>();
         ArrayList<lamps> Lamps=new ArrayList<lamps>();


        lampsRecyclerAdapter adapter =new lampsRecyclerAdapter(this);
        adapter.setLamps(Lamps);


        lampsRecyclerView.setAdapter(adapter);
        lampsRecyclerView.setLayoutManager(new GridLayoutManager(this,2));


        // Geting the first values from database
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Led");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Val.clear();
                NameOfPin.clear();

                for (DataSnapshot Snapshot:snapshot.getChildren()) {

                    Val.add(Snapshot.getValue().toString());
                    NameOfPin.add(Snapshot.getKey());
                    Lamps.add(new lamps(Snapshot.getKey(),
                            Snapshot.getValue().toString()));

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        mDBase=FirebaseDatabase.getInstance().getReference().child("LedNames");
        mDBase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                i=0;

                for (DataSnapshot Snapshot:snapshot.getChildren()) {

                    NameOfRoom.add(Snapshot.getValue().toString());
                    Lamps.get(i).setName(Snapshot.getValue().toString());
                    i++;



                }
                adapter.notifyDataSetChanged();
                System.out.println(NameOfRoom.size());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        // Adding button, clicking it
        lampsAddButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivityAddingLamp();

            }
        });


        lampsDeleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clearAdapterData();
                System.out.println(adapter.getItemCount());
                openactivityDelLamps();
            }
        });






        //



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                openactivity2();
            }
        });

        goToSensorsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenActivitySensors();
            }
        });
    }

    private void openactivityAddingLamp() {
        Intent i=new Intent(this,Adding_Lamp.class);
        startActivity(i);
    }

    private void openactivity2() {
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);
    }

    private void openactivityDelLamps() {
        Intent i=new Intent(this,DeletingLambs.class);
        startActivity(i);
    }

    private void OpenActivitySensors() {
        Intent i=new Intent(this,SensorsPage.class);
        startActivity(i);
        finish();
    }

}