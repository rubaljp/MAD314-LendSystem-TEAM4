package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.interface_api.ApiClient;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.pojo_class.Add_item_pojo;
import com.example.finalproject.user.BookIssue;
import com.example.finalproject.user.User_ItemList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView view_item,issuebook,Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_item = findViewById(R.id.view_item);
        issuebook = findViewById(R.id.issuebook);
        Logout = findViewById(R.id.Logout);

        view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, User_ItemList.class);
                startActivity(i);
            }
        });


        issuebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, BookIssue.class);
                startActivity(i);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (GlobalClass.isNetworkConnected(MainActivity.this)) {



                        Logut(MainActivity.this, CSPreferences.readString(MainActivity.this,"sessioniid"));


                        } else {

                            Toast.makeText(MainActivity.this, R.string.nointernet, Toast.LENGTH_LONG).show();


                        }

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
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        CSPreferences.clearPref(MainActivity.this);
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
