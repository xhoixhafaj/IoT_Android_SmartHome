package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class DeletingLambs extends AppCompatActivity {
    private Spinner spinner;
    private Button buttonDelLamp;
    private Button buttonExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_lambs);

        spinner=findViewById(R.id.spinner);
        buttonDelLamp=findViewById(R.id.buttonDelLamp);
        buttonExit=findViewById(R.id.buttonExit);

        ArrayList<String> data=new ArrayList<>();
        data.add("No Selection");

        FirebaseDatabase.getInstance().getReference().child("LedNames").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()
                     ) {
                    String a =s.getValue().toString();
                    System.out.println(a);
                    data.add(a);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> dataAD= new ArrayAdapter<>(
                this,R.layout.support_simple_spinner_dropdown_item,data
        );

        spinner.setAdapter(dataAD);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(DeletingLambs.this, data.get(i)+" selcted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonDelLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("LedNames").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (spinner.getSelectedItem().toString().equals("No Selection")){
                            Toast.makeText(DeletingLambs.this, "Select the item you want to delete", Toast.LENGTH_SHORT).show();}
                        else {

                        for (DataSnapshot s:snapshot.getChildren()
                             ) {
                            if (s.getValue().toString().equals(spinner.getSelectedItem().toString())) {


                                FirebaseDatabase.getInstance().getReference().child("Led").child(s.getKey()).removeValue();
                                FirebaseDatabase.getInstance().getReference().child("LedNames").child(s.getKey()).removeValue();
                            }

                        }
                        openactivity();
                        finish();
                    }}

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity();
                finish();
            }
        });


    }
    private void openactivity() {
        Intent i=new Intent(this,firstActivity.class);
        startActivity(i);
    }
}