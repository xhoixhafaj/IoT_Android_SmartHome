package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Adding_Lamp extends AppCompatActivity {
    private ArrayList<String> Pins=new ArrayList<String>();
    private String PinsChoosen;
    private Spinner spinnerOfPins;
    private Context context;
    private DatabaseReference mDBase;
    private Button addAddButton;
    private EditText addTxtRoomName;
    private Button addExitButton;

    public Adding_Lamp(Context context) {
        this.context = context;
    }
    public Adding_Lamp(){
    }

    public String getPinsChoosen() {
        return PinsChoosen;
    }

    public void setPinsChoosen(String pinsChoosen) {
        PinsChoosen = pinsChoosen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_lamp);
        Pins.add("NoPin");
        Pins.add("Pin1");
        Pins.add("Pin14");
        Pins.add("Pin18");
        Pins.add("Pin19");
        Pins.add("Pin11");
        Pins.add("Pin13");
        Pins.add("Pin9");

        //Removing choosen pins

        mDBase= FirebaseDatabase.getInstance().getReference().child("LedNames");
        mDBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot Snapshot:snapshot.getChildren()
                     ) {
                    Pins.remove(Snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        spinnerOfPins=findViewById(R.id.spinnerOfPins);
        addAddButton=findViewById(R.id.addAddButton);
        addTxtRoomName=findViewById(R.id.addTxtRoomName);
        addExitButton=findViewById(R.id.addExitButton);

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Pins);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerOfPins.setAdapter(dataAdapter);

        HashMap<String, Object> map=new HashMap<>();
        HashMap<String, Object> mapVal=new HashMap<>();
        FirebaseDatabase.getInstance().getReference().child("Led").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot Snapshot:snapshot.getChildren()
                     ) {
                    mapVal.put(Snapshot.getKey(),Snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("LedNames").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()
                     ) {
                    map.put(s.getKey(),s.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        addAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinnerOfPins.getSelectedItem().toString().equals("NoPin")){
                    Toast.makeText(Adding_Lamp.this,"Plese select a pin",Toast.LENGTH_SHORT).show();
                } else {
                    map.put(spinnerOfPins.getSelectedItem().toString(),addTxtRoomName.getText().toString());
                    mapVal.put(spinnerOfPins.getSelectedItem().toString(),"off");
                    FirebaseDatabase.getInstance().getReference().child("LedNames").setValue(map);
                    FirebaseDatabase.getInstance().getReference().child("Led").setValue(mapVal);

                    openFirstActivity();
                    finish();
                }
            }
        });

        addExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstActivity();
                finish();
            }
        });




    }
    private void openFirstActivity() {
        Intent i=new Intent(this,firstActivity.class);
        startActivity(i);
    }
}