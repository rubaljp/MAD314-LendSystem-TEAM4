package com.example.finalproject;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.GlobalClass;
import com.example.finalproject.LoginActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.SignUp;
import com.example.finalproject.interface_api.ApiClient;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.pojo_class.Add_item_pojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_MainActivity extends AppCompatActivity {

    TextView view_item,Add_item,Logout,manage_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__main);

        view_item = findViewById(R.id.view_item);
        Add_item = findViewById(R.id.Add_item);
        Logout = findViewById(R.id.Logout);
        manage_item = findViewById(R.id.manage_item);

        view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Admin_MainActivity.this, View_all_list.class);
                startActivity(i);
            }
        });
        manage_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Admin_MainActivity.this, Manage_item.class);
                startActivity(i);
            }
        });

        Add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Admin_MainActivity.this, Add_items.class);
                startActivity(i);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_MainActivity.this);
                builder.setTitle("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Logut(Admin_MainActivity.this, CSPreferences.readString(Admin_MainActivity.this,"sessioniid"));
                    }
                })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                            }

                        });

                builder.show();
            }
        });

    }


    public void Logut(final Context context, String sessionid) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().logout(sessionid);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                try {
                    if (response.body().getStatus() ==  200) {
                        Intent intent = new Intent(Admin_MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        CSPreferences.clearPref(Admin_MainActivity.this);
                        GlobalClass.showtost(context, "" + response.body().getMessage());
                    } else {
                        GlobalClass.showtost(context, "" + response.body().getMessage());
                    }
                }catch (Exception e){
                    GlobalClass.showtost(context, "" + "Item Already Issue");

                }


            }

            @Override
            public void onFailure(Call<Add_item_pojo> call, Throwable t) {

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
