package com.cheatcodesexample.fo.challenge1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private EditText LoginTextName;
    private EditText LoginTextPass;
    private Button LoginButtonLogin;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginTextName=findViewById(R.id.LoginTextName);
        LoginTextPass=findViewById(R.id.LoginTextPass);
        LoginButtonLogin=findViewById(R.id.LoginButtonLogin);

        Auth=FirebaseAuth.getInstance();

        LoginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = LoginTextName.getText().toString();
                String txt_password = LoginTextPass.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(LoginActivity.this, "Please enter the email and the password", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 4) {
                    Toast.makeText(LoginActivity.this, "Password to short", Toast.LENGTH_SHORT).show();

                } else {
                    loginuser(txt_email,txt_password);

                }

            }

        });


    }

    private void loginuser(String email,String password) {

    Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
            Toast.makeText(LoginActivity.this, "Login succesfull", Toast.LENGTH_SHORT).show();
            openfirstactivity();
            finish();
        }
    });

    }
    private void openfirstactivity() {
        Intent i=new Intent(this,firstActivity.class);
        startActivity(i);
    }
}