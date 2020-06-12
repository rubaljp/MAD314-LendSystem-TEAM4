package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminMainActivity extends AppCompatActivity {

    TextView view_item,Add_item,Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__main);

        view_item = findViewById(R.id.view_item);
        Add_item = findViewById(R.id.Add_item);
        Logout = findViewById(R.id.Logoutt);

        view_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(AdminMainActivity.this, ViewAllItems.class);
                startActivity(i);
            }
        });
        Add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(AdminMainActivity.this, AddItems.class);
                startActivity(i);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }
}

