package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalproject.adpter.Issue_bookAdapter;

public class BookIssue extends AppCompatActivity {
    RecyclerView bookissue;
    Issue_bookAdapter issue_bookAdapter;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);

        bookissue =findViewById(R.id.bookissue);


        LinearLayoutManager LayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookissue.setLayoutManager(LayoutManagaer);

        issue_bookAdapter = new Issue_bookAdapter(this);
        bookissue.setAdapter(issue_bookAdapter);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

