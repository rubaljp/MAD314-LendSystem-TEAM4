package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreate;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.usernameLogin);
        mPassword=findViewById(R.id.passwordLogin);
        firebaseAuth=FirebaseAuth.getInstance();
        mLoginBtn=findViewById(R.id.buttonLog);
        mCreate=(TextView) findViewById(R.id.tvSign);

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

      mLoginBtn.setOnClickListener(new View.OnClickListener() {
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
              firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(LoginActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),EmptyPage.class));
                      }else {
                          Toast.makeText(LoginActivity.this,"error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
              });
          }
      });
    }
}
