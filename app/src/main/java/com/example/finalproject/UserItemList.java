package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.adpter.Admin_allAdapter;
import com.example.finalproject.adpter.User_item_Adapter;
import com.example.finalproject.interface_api.Admin_item_interface;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;

import java.util.ArrayList;

public class User_ItemList extends AppCompatActivity implements Admin_item_interface {
    RecyclerView viewall_item;
    User_item_Adapter user_item_adapter;
    ArrayList<AdminItems_list_pojo.ItemList>  arrayLists = new ArrayList<>();
    RadioGroup radio_group;
    RadioButton elect,book;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_list);


        searchView =findViewById(R.id.searchView);
        viewall_item =findViewById(R.id.viewall_item);
        radio_group =findViewById(R.id.radio_group);


        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        viewall_item.setLayoutManager(LayoutManagaer);

        user_item_adapter = new User_item_Adapter(this,arrayLists);
        viewall_item.setAdapter(user_item_adapter);
        int selectedId = radio_group.getCheckedRadioButtonId();
        elect = findViewById(R.id.elect);
        book = findViewById(R.id.book);

        elect.setChecked(true);


        WebApicall webApicall = new WebApicall();
        webApicall.items_list(this, "","1",this);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.d("fddfsd",s);

                user_item_adapter.getFilter().filter(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                user_item_adapter.getFilter().filter(s);

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
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.elect:
                if(checked)
                    check("1");

                break;
            case R.id.book:
                if(checked)
                    check("2");
                break;

        }

    }

    private void check(String s) {
        if (s.equals("1")){
            WebApicall webApicall = new WebApicall();
            webApicall.items_list(this, "","1",this);
        }else {
            WebApicall webApicall = new WebApicall();
            webApicall.items_list(this, "","2",this);
        }

    }

}

