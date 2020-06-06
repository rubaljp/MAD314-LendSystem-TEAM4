package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView donotaccount;
    EditText email,password;
    Button login;

    RadioButton admin,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        donotaccount=findViewById(R.id.donotaccount);
        email=findViewById(R.id.usernameLogin);
        password=findViewById(R.id.passwordLogin);
        login=findViewById(R.id.buttonsubmit);
        admin=findViewById(R.id.admin);
        user=findViewById(R.id.user);


        donotaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
            }
        });

    }




    public void validation(){
        if (email.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please enter valid email");
        }else  if (password.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please enter password");
        }else {

            if (admin.getText().toString().equals("admin")){
                Intent i=new Intent(LoginActivity.this, Admin_MainActivity.class);
                startActivity(i);
            }else  if (user.getText().toString().equals("User")){
                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }


        }
    }


}
