package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalproject.adpter.User_item_Adapter;

public class UserItemList extends AppCompatActivity {
    RecyclerView viewall_item;
    User_item_Adapter user_item_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_list);


        viewall_item =findViewById(R.id.viewall_item);


        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewall_item.setLayoutManager(LayoutManagaer);

        user_item_adapter = new User_item_Adapter(this);
        viewall_item.setAdapter(user_item_adapter);


    }
}
