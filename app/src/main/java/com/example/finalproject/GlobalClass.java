package com.example.finalproject;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.widget.Toast;


public class GlobalClass {

    Context context;

   /* // Call fragmnet
    public static void fragment(FragmentActivity fragmentActivity, Fragment fragment, boolean addToBackStack){
        if (addToBackStack){
             fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, fragment).addToBackStack(null).commit();
        }else {
          fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, fragment).commit();
        }
    }*/


    public static void dailogshow(Context context){
        ProgressDialog pd;

        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public static void dailoghide(Context context){
        ProgressDialog pd;
        pd = new ProgressDialog(context);
        pd.dismiss();
    }


    // Show toast
    public static void showtost(Context context,String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    // Email validation
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static void progressdailogShow(Context context, ProgressDialog pd){
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }
    public static void progressdailogHide(Context context, ProgressDialog pd){
        pd = new ProgressDialog(context);
        pd.dismiss();
    }
    public static boolean isNetworkConnected( Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
