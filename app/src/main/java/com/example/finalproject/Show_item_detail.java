package com.example.finalproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.WebApicall;

import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class Show_item_detail extends AppCompatActivity {

    ImageView item_pic;
    TextView tital,discraption,issuedate,retrun_date;
    Button confirm;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_detail);

        item_pic=findViewById(R.id.item_pic);
        tital=findViewById(R.id.tital);
        discraption=findViewById(R.id.discraption);
        issuedate=findViewById(R.id.issuedate);
        retrun_date=findViewById(R.id.retrun_date);
        confirm=findViewById(R.id.confirm);

        final Bundle extras = getIntent().getExtras();
        tital.setText(extras.getString("tital"));
        discraption.setText(extras.getString("discraption"));

        Glide.with(this)
                .load(extras.getString("image"))
                .fitCenter()
                .placeholder(R.drawable.logo)
                .into(item_pic);





        issuedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Show_item_detail.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                issuedate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        retrun_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Show_item_detail.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                retrun_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (issuedate.getText().toString().length() == 0){
                    GlobalClass.showtost(Show_item_detail.this,"Select your issue date");
                }else  if (retrun_date.getText().toString().length() == 0){
                    GlobalClass.showtost(Show_item_detail.this,"Select your retrun date");

                }else {
                    if (GlobalClass.isNetworkConnected(Show_item_detail.this)) {

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("session_id", CSPreferences.readString(Show_item_detail.this,"sessioniid"));
                        hashMap.put("item_id",""+extras.getInt("itemid"));
                        hashMap.put("issued_date",issuedate.getText().toString());
                        hashMap.put("return_date",retrun_date.getText().toString());

                        WebApicall webApicall = new WebApicall();
                        webApicall.user_item_issued(Show_item_detail.this,hashMap);
                    } else {

                        Toast.makeText(Show_item_detail.this, R.string.nointernet, Toast.LENGTH_LONG).show();


                    }

                }
            }
        });


    }
}
