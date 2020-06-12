package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalproject.adpter.Admin_allAdapter;

public class ViewAllItems extends AppCompatActivity {

    RecyclerView viewall_item;
    Admin_allAdapter admin_allAdapter;
    ImageView back,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_list);

        viewall_item =findViewById(R.id.viewall_item);


        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewall_item.setLayoutManager(LayoutManagaer);

        admin_allAdapter = new Admin_allAdapter(this);
        viewall_item.setAdapter(admin_allAdapter);

        back=findViewById(R.id.back);
        add=findViewById(R.id.add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewAllItems.this, Add_items.class);
                startActivity(i);
            }
        });

    }
}