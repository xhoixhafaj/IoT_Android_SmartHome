package com.cheatcodesexample.fo.challenge1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Button SmartButtonContinue;
    private CheckBox TermsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartButtonContinue=findViewById(R.id.SmartButtonContinue);
        TermsCheckBox=findViewById(R.id.TermsCheckBox);

        SmartButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TermsCheckBox.isChecked()){

                    openActivity2();
                } else {
                    Toast.makeText(MainActivity.this,"Please accept the terms and services",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    public void openActivity2(){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);

    }
}