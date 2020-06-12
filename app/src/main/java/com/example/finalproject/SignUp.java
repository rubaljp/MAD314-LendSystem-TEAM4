package com.example.finalproject;

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

public class SignUp extends AppCompatActivity {


    Button buttonsubmit,cancal;
    FirebaseAuth fAuth;
    EditText usernameLogin,name,phone_number,email,password,c_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonsubmit=findViewById(R.id.buttonsubmit);
        cancal=findViewById(R.id.cancal);
        usernameLogin=findViewById(R.id.usernameLogin);
        name=findViewById(R.id.name);
        phone_number=findViewById(R.id.phone_number);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        c_password=findViewById(R.id.c_password);
        fAuth=FirebaseAuth.getInstance();


        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId=email.getText().toString().trim();

                String passwordd=password.getText().toString().trim();
                if (TextUtils.isEmpty(emailId)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(passwordd)){
                    email.setError("Password is required");
                    return;
                }

                if (passwordd.length()<6){
                    password.setError("Password should be of aleast 7");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(emailId,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUp.this,"USER SIGNED UP SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent (getApplicationContext(), Admin_MainActivity.class));
                    }else  {
                        Toast.makeText(SignUp.this,"ERROR !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });
        cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}