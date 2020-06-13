package com.example.finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalproject.Admin_MainActivity;
import com.example.finalproject.interface_api.ApiClient;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.pojo_class.Register_model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    RadioGroup group;

    RadioButton admin,user;
    Button buttonsubmit,cancal;

    EditText name,phone_number,email,password,c_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonsubmit=findViewById(R.id.buttonsubmit);
        cancal=findViewById(R.id.cancal);
        group=findViewById(R.id.group);

        name=findViewById(R.id.name);
        phone_number=findViewById(R.id.phone_number);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        c_password=findViewById(R.id.c_password);
        buttonsubmit=findViewById(R.id.buttonsubmit);

        cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaiton();
            }
        });

    }

    private void validaiton(){
        if (name.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please Enter Name");

        }else if (phone_number.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please Enter Phone Number");
        }else if (email.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please enter valid email");
        }else if (password.getText().toString().length()  == 0){
            GlobalClass.showtost(this," Please enter password");
        }else if (c_password.getText().toString().equals(password.getText().toString())){
            int selectedId=group.getCheckedRadioButtonId();
            admin=findViewById(selectedId);

            if (admin.getText().toString().equals("Admin")){

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("phone",password.getText().toString());
                hashMap.put("password",password.getText().toString());
                hashMap.put("confirm_password",c_password.getText().toString());
                hashMap.put("usertype","2");
                hashMap.put("device_type","1");
                hashMap.put("device_token","11111111111111111111");


                registerUser(SignUp.this, hashMap);

            }else  if (admin.getText().toString().equals("User")){
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("phone",password.getText().toString());
                hashMap.put("password",password.getText().toString());
                hashMap.put("confirm_password",c_password.getText().toString());
                hashMap.put("usertype","1");
                hashMap.put("device_type","1");
                hashMap.put("device_token","11111111111111111111");


                registerUser(SignUp.this, hashMap);
            }



        }else {
            GlobalClass.showtost(this,"Password do not match");
        }
    }



    public void registerUser(final Context context, HashMap<String, String> hashMap) {
        dailogshow(context);
        Call<Register_model> userpost_responseCall = ApiClient.getClient().registerUser(hashMap);
        userpost_responseCall.enqueue(new Callback<Register_model>() {
            @Override
            public void onResponse(Call<Register_model> call, Response<Register_model> response) {
                dailoghide(context);

                if (response.body().getSuccess() ==  true) {

                    // GlobalClass.showtost(context, "" + response.body().getMessage());
                    //if (response.body().getStatus().equals("SUCCESS")) {
                    CSPreferences.putString(context, "sessioniid", String.valueOf(response.body().getProfile().getSessionId()));

                    CSPreferences.putString(context,"id",String.valueOf(response.body().getProfile().getUserId()));
                    CSPreferences.putString(context,"email",String.valueOf(response.body().getProfile().getEmail()));
                    CSPreferences.putString(context,"name",String.valueOf(response.body().getProfile().getUserName()));

                    if (response.body().getProfile().getUsertype().equals("2")){
                        Intent i=new Intent(SignUp.this, Admin_MainActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        Intent i=new Intent(SignUp.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Register_model> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }
    ProgressDialog pd;

    public void dailogshow(Context context) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public void dailoghide(Context context) {
        pd.dismiss();
    }
}