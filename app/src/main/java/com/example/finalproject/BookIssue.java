package com.example.finalproject;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.adpter.Issue_bookAdapter;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.Issued_item_list_interface;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookIssue extends AppCompatActivity implements Issued_item_list_interface {
    RecyclerView bookissue;
    Issue_bookAdapter issue_bookAdapter;

    ImageView back;
    ArrayList<Issued_item_list_pojo.IssuedList> arrayLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);

        bookissue =findViewById(R.id.bookissue);


        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookissue.setLayoutManager(LayoutManagaer);

        issue_bookAdapter = new Issue_bookAdapter(this,arrayLists);
        bookissue.setAdapter(issue_bookAdapter);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (GlobalClass.isNetworkConnected(BookIssue.this)) {



            WebApicall webApicall =new WebApicall();
            webApicall.issued_item_list(this, CSPreferences.readString(this,"sessioniid"),this);
        } else {

            Toast.makeText(this, R.string.nointernet, Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void Issued_item_list(ArrayList<Issued_item_list_pojo.IssuedList> arrayList) {
        arrayLists.clear();
        arrayLists.addAll(arrayList);
        bookissue.getAdapter().notifyDataSetChanged();

    }
}
