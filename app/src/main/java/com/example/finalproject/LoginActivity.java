package com.example.finalproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.admin.Admin_MainActivity;
import com.example.finalproject.interface_api.ApiClient;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.pojo_class.Register_model;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView donotaccount, forgot;
    EditText email, password;
    Button login;
    RadioGroup group;

    RadioButton admin, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkpermissions(this);
        donotaccount = findViewById(R.id.donotaccount);
        email = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.buttonLogin);
        admin = findViewById(R.id.admin);
        user = findViewById(R.id.user);
        forgot = findViewById(R.id.forgot);
        group = findViewById(R.id.group);
        admin.setChecked(true);

        donotaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUp.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, Forgot_password.class);
                startActivity(i);
            }
        });


    }


    public void validation() {
        if (email.getText().toString().length() == 0) {
            GlobalClass.showtost(this, " Please enter valid email");
        } else if (password.getText().toString().length() == 0) {
            GlobalClass.showtost(this, " Please enter password");
        } else {
            int selectedId = group.getCheckedRadioButtonId();
            admin = findViewById(selectedId);
            Log.d("dfsdfsdf", admin.getText().toString());
            Log.d("dfsdfsdf", user.getText().toString());
            if (admin.getText().toString().equals("Admin")) {


                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email", email.getText().toString());
                    hashMap.put("password", password.getText().toString());
                    hashMap.put("usertype", "2");
                    hashMap.put("device_type", "1");
                    hashMap.put("device_token", "11111111111111111111");


                    LoginApi(LoginActivity.this, hashMap);
                } else {

                    Toast.makeText(this, R.string.nointernet, Toast.LENGTH_LONG).show();
                }



            } else if (admin.getText().toString().equals("User")) {
                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", email.getText().toString());
                hashMap.put("password", password.getText().toString());
                hashMap.put("usertype", "1");
                hashMap.put("device_type", "1");
                hashMap.put("device_token", "11111111111111111111");

                LoginApi(LoginActivity.this, hashMap);
                } else {

                    Toast.makeText(this, R.string.nointernet, Toast.LENGTH_LONG).show();
                }
            }


        }
    }

    public void LoginApi(final Context context, HashMap<String, String> hashMap) {
        dailogshow(context);
        Call<Register_model> userpost_responseCall = ApiClient.getClient().loginUser(hashMap);
        userpost_responseCall.enqueue(new Callback<Register_model>() {
            @Override
            public void onResponse(Call<Register_model> call, Response<Register_model> response) {
                dailoghide(context);

                if (response.body().getSuccess() == true) {

                    // GlobalClass.showtost(context, "" + response.body().getMessage());
                    //if (response.body().getStatus().equals("SUCCESS")) {

                    CSPreferences.putString(context, "sessioniid", String.valueOf(response.body().getProfile().getSessionId()));
                    CSPreferences.putString(context, "id", String.valueOf(response.body().getProfile().getUserId()));
                    CSPreferences.putString(context, "email", String.valueOf(response.body().getProfile().getEmail()));
                    CSPreferences.putString(context, "name", String.valueOf(response.body().getProfile().getUserName()));

                    if (response.body().getProfile().getUsertype().equals("2")) {
                        Intent i = new Intent(LoginActivity.this, Admin_MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
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

    public  void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .check();
        }


    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

        }
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {

        }

    };


}
