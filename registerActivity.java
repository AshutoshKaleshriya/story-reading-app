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

public class registerActivity extends AppCompatActivity {
    private Button f_lgnbtn;
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterbtn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        f_lgnbtn= findViewById(R.id.f_lgnbtn);
        f_lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lgn = new Intent(registerActivity.this,loginActivity.class);
                startActivity(lgn);
            }
        });


        mFullName = findViewById(R.id.username);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.c_password);
        mRegisterbtn = findViewById(R.id.registerbtn);
        fAuth = FirebaseAuth.getInstance();


        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
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
                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registerActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),homeActivity.class));
                        }
                        else {
                            Toast.makeText(registerActivity.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}