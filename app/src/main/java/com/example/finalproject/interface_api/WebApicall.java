package com.example.finalproject.interface_api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.GlobalClass;
import com.example.finalproject.LoginActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.SignUp;

import com.example.finalproject.Admin_MainActivity;
import com.example.finalproject.pojo_class.Add_item_pojo;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;
import com.example.finalproject.pojo_class.Register_model;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebApicall {

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


    public void items_list(final Context context, String sessionid,String type, final Admin_item_interface admin_item_interface) {
        dailogshow(context);
        Call<AdminItems_list_pojo> userpost_responseCall = ApiClient.getClient().items_list(sessionid,type);
        userpost_responseCall.enqueue(new Callback<AdminItems_list_pojo>() {
            @Override
            public void onResponse(Call<AdminItems_list_pojo> call, Response<AdminItems_list_pojo> response) {
                dailoghide(context);

                if (response.body().getStatus() ==  200) {

                    admin_item_interface.itemlist((ArrayList<AdminItems_list_pojo.ItemList>) response.body().getItemList());

                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<AdminItems_list_pojo> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void user_item_issued(final Context context, HashMap<String, String> hashMap) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().user_item_issued(hashMap);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                try {
                    if (response.body().getStatus() ==  200) {
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

    public void forgot_password(final Context context, String email) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().forgot_password(email);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                try {
                    if (response.body().getStatus() ==  200) {
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


    public void insert_items(final Context context, RequestBody session_id, RequestBody name, RequestBody description, RequestBody no_of_items, RequestBody type , MultipartBody.Part image) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().insert_items(session_id,name,description,no_of_items,type,image);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                if (response.body().getStatus() ==  200) {
                    GlobalClass.showtost(context, "" + response.body().getMessage());

                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
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

    public void update_del_items(final Context context, RequestBody session_id, RequestBody name, RequestBody description, RequestBody no_of_items, RequestBody type , RequestBody item_id , RequestBody item_type , MultipartBody.Part image) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().update_del_items(session_id,name,description,no_of_items,type,item_id,item_type,image);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                if (response.body().getStatus() ==  200) {
                    GlobalClass.showtost(context, "" + response.body().getMessage());

                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
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



    public void issued_item_list(final Context context, String sessionid, final Issued_item_list_interface issued_item_list_interface) {
        dailogshow(context);
        Call<Issued_item_list_pojo> userpost_responseCall = ApiClient.getClient().issued_item_list(sessionid);
        userpost_responseCall.enqueue(new Callback<Issued_item_list_pojo>() {
            @Override
            public void onResponse(Call<Issued_item_list_pojo> call, Response<Issued_item_list_pojo> response) {
                dailoghide(context);

                if (response.body().getStatus() ==  200) {

                    issued_item_list_interface.Issued_item_list((ArrayList<Issued_item_list_pojo.IssuedList>) response.body().getIssuedList());

                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Issued_item_list_pojo> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void return_issued_item(final Context context, String sessionid,String item_id) {
        dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().return_issued_item(sessionid,item_id);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                dailoghide(context);

                try {
                    if (response.body().getStatus() ==  200) {

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

}
