package com.cheatcodesexample.fo.challenge1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button LoginButtonA2;
    private Button RegisterButtonA2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LoginButtonA2=findViewById(R.id.LoginButtonA2);
        RegisterButtonA2=findViewById(R.id.RegisterButtonA2);

        RegisterButtonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        LoginButtonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();

            }
        });



    }

    @Override
    public void onBackPressed() {

    }
    public void openActivity3(){
        Intent i=new Intent(this,MainActivity3.class);
        startActivity(i);

    }

    public void openLoginActivity(){
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);

    }


}