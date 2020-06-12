package com.example.finalproject.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.ViewHolder>{

    Context context;

    // RecyclerView recyclerView;
    public UserItemAdapter(Context context) {
        this.context = context;

    }
    @Override
    public UserItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        UserItemAdapter.ViewHolder viewHolder = new UserItemAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserItemAdapter.ViewHolder holder, int position) {

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



