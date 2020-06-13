package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalproject.R;
import com.example.finalproject.adpter.Issue_bookAdapter;
import com.example.finalproject.adpter.User_item_Adapter;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.Issued_item_list_interface;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;

import java.util.ArrayList;

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


        WebApicall webApicall =new WebApicall();
        webApicall.issued_item_list(this, CSPreferences.readString(this,"sessioniid"),this);

    }

    @Override
    public void Issued_item_list(ArrayList<Issued_item_list_pojo.IssuedList> arrayList) {
        arrayLists.clear();
        arrayLists.addAll(arrayList);
        bookissue.getAdapter().notifyDataSetChanged();

    }
}


