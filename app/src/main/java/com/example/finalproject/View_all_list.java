package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.finalproject.R;
import com.example.finalproject.adpter.Admin_allAdapter;
import com.example.finalproject.interface_api.Admin_item_interface;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;

import java.util.ArrayList;

public class View_all_list extends AppCompatActivity implements Admin_item_interface {

    RecyclerView viewall_item;
    Admin_allAdapter admin_allAdapter;
    ImageView back,add;
    ArrayList<AdminItems_list_pojo.ItemList>  arrayLists = new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_list);
        viewall_item =findViewById(R.id.viewall_item);
        searchView =findViewById(R.id.searchView);

        LinearLayoutManager     LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewall_item.setLayoutManager(LayoutManagaer);

        admin_allAdapter = new Admin_allAdapter(this,arrayLists);
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
                Intent i=new Intent(View_all_list.this, Add_items.class);
                startActivity(i);
            }
        });

        WebApicall webApicall = new WebApicall();
        webApicall.items_list(this, CSPreferences.readString(this,"sessioniid"),"",this);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.d("fddfsd",s);

                admin_allAdapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                admin_allAdapter.getFilter().filter(s);

                return false;
            }


        });

    }

    @Override
    public void itemlist(ArrayList<AdminItems_list_pojo.ItemList> arrayList) {
        arrayLists.clear();
        arrayLists.addAll(arrayList);
        viewall_item.getAdapter().notifyDataSetChanged();

    }
}
