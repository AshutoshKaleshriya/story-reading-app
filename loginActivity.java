package com.example.storyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private Button f_registerbtn;
    EditText mEmail,mPassword;
    Button mloginbtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        f_registerbtn= findViewById(R.id.f_rgsbtn);
        f_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rgster = new Intent(loginActivity.this,registerActivity.class);
                startActivity(rgster);
            }
        });

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mloginbtn = findViewById(R.id.loginbtn);
        fAuth = FirebaseAuth.getInstance();

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                }
                if(TextUtils.isEmpty(password)){
                    mEmail.setError("password is required.");
                }
                if (password.length()<8){
                    mPassword.setError("password must be greater than 8 character");
                    return;
                }
                // Authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),homeActivity.class));
                        }
                        else {
                            Toast.makeText(loginActivity.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}