package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mSignUp,mLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mEmail=findViewById(R.id.editTextUsername);
        mPassword=findViewById(R.id.editTextPassword);
        mSignUp=findViewById(R.id.buttonSign);
        mLogin=findViewById(R.id.buttonLogin);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);





        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                if (password.length()<6){
                    mPassword.setError("Password must be greater then 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

               fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this,"user created",Toast.LENGTH_SHORT).show();
                         //   startActivity(new Intent(getApplicationContext(),EmptyPage.class));
                        }
                        else {
                            Toast.makeText(SignUp.this,"error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });      



            }
        });



    }
}