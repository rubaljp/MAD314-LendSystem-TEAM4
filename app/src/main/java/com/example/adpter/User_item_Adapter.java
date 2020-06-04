package com.example.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class User_item_Adapter extends RecyclerView.Adapter<User_item_Adapter.ViewHolder>{

    Context context;

    // RecyclerView recyclerView;
    public User_item_Adapter(Context context) {
        this.context = context;

    }
    @Override
    public User_item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        User_item_Adapter.ViewHolder viewHolder = new User_item_Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(User_item_Adapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}



