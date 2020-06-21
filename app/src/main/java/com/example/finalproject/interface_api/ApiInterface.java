package com.example.finalproject.interface_api;

import com.example.finalproject.pojo_class.Add_item_pojo;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;
import com.example.finalproject.pojo_class.Register_model;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("loginUser")
    Call<Register_model> loginUser(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("registerUser")
    Call<Register_model> registerUser(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("items_list")
    Call<AdminItems_list_pojo> items_list(@Field("session_id") String sessionid,@Field("type") String type);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<Add_item_pojo> forgot_password(@Field("email") String email);


    @FormUrlEncoded
    @POST("user_item_issued")
    Call<Add_item_pojo> user_item_issued(@FieldMap HashMap<String, String> hashMap);

    @Multipart
    @POST("insert_items")
    Call<Add_item_pojo> insert_items(@Part("session_id") RequestBody session_id,
                                     @Part("name")RequestBody name,
                                     @Part("description")RequestBody description,
                                     @Part("no_of_items")RequestBody no_of_items,
                                     @Part("type")RequestBody type,
                                     @Part MultipartBody.Part  file);


   @Multipart
    @POST("update_del_items")
    Call<Add_item_pojo> update_del_items(@Part("session_id") RequestBody session_id,
                                     @Part("name")RequestBody name,
                                     @Part("description")RequestBody description,
                                     @Part("no_of_items")RequestBody no_of_items,
                                     @Part("type")RequestBody type,
                                     @Part("item_id")RequestBody item_id,
                                     @Part("item_type")RequestBody item_type,
                                     @Part MultipartBody.Part  file);


    @FormUrlEncoded
    @POST("issued_item_list")
    Call<Issued_item_list_pojo> issued_item_list(@Field("session_id") String session_id);


    @FormUrlEncoded
    @POST("logout")
    Call<Add_item_pojo> logout(@Field("session_id") String session_id);

    @FormUrlEncoded
    @POST("return_issued_item")
    Call<Add_item_pojo> return_issued_item(@Field("session_id") String session_id,@Field("item_id") String item_id);


}
