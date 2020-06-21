package com.example.finalproject.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.GlobalClass;
import com.example.finalproject.R;
import com.example.finalproject.SignUp;
import com.example.finalproject.adpter.Admin_allAdapter;
import com.example.finalproject.adpter.ManageItem;
import com.example.finalproject.interface_api.Admin_item_interface;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;

import java.util.ArrayList;

public class Manage_item extends AppCompatActivity  implements Admin_item_interface {
    RecyclerView viewall_item;
    ManageItem manageItemadapter;
    ImageView back;
    ImageView add;
    ArrayList<AdminItems_list_pojo.ItemList> arrayLists = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);
        viewall_item =findViewById(R.id.viewall_item);
        searchView =findViewById(R.id.searchView);

        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewall_item.setLayoutManager(LayoutManagaer);

        manageItemadapter = new ManageItem(this,arrayLists);
        viewall_item.setAdapter(manageItemadapter);

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
                Intent i=new Intent(Manage_item.this, Add_items.class);
                startActivity(i);
            }
        });
        if (GlobalClass.isNetworkConnected(Manage_item.this)) {
        WebApicall webApicall = new WebApicall();
        webApicall.items_list(this, CSPreferences.readString(this,"sessioniid"),"",this);
        } else {

            Toast.makeText(this, R.string.nointernet, Toast.LENGTH_LONG).show();


        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.d("fddfsd",s);

                manageItemadapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                manageItemadapter.getFilter().filter(s);

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
