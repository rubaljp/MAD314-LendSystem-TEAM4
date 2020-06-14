package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.interface_api.WebApicall;

public class Forgot_password extends AppCompatActivity {

    EditText email;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().length() == 0){
                    GlobalClass.showtost(Forgot_password.this,"Please enter Valid Email");
                }else {

                    if (GlobalClass.isNetworkConnected(Forgot_password.this)) {
                        WebApicall webApicall = new WebApicall();
                        webApicall.forgot_password(Forgot_password.this,email.getText().toString());
                    } else {
                        Toast.makeText(Forgot_password.this, R.string.nointernet, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });




    }
}
