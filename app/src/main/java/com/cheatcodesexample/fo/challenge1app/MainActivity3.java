package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity3 extends AppCompatActivity {

    private TextView editTextActivity3Id1;
    private TextView editTextActivity3Id2;
    private Button btnActivity3Id1;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTextActivity3Id1 = findViewById(R.id.editTextActivity3Id1);
        editTextActivity3Id2 = findViewById(R.id.editTextActivity3Id2);
        btnActivity3Id1 = findViewById(R.id.btnActivity3Id1);
        auth=FirebaseAuth.getInstance();

        btnActivity3Id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = editTextActivity3Id1.getText().toString();
                String txt_password = editTextActivity3Id2.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(MainActivity3.this, "Please enter the email and the password", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 4) {
                    Toast.makeText(MainActivity3.this, "Password to short", Toast.LENGTH_SHORT).show();

                } else {
                    registerUser(txt_email, txt_password);
                }

            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity3.this,"Registration succesfull",Toast.LENGTH_SHORT).show();
                   openfirstactivity();
                   finish();
                } else {
                    Toast.makeText(MainActivity3.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openfirstactivity() {
        Intent i=new Intent(this,firstActivity.class);
        startActivity(i);
    }
}