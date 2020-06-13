package com.example.finalproject.adpter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class Issue_bookAdapter extends RecyclerView.Adapter<Issue_bookAdapter.ViewHolder>{

    Context context;
    ArrayList<Issued_item_list_pojo.IssuedList> arrayLists;
    // RecyclerView recyclerView;
    public Issue_bookAdapter(Context context, ArrayList<Issued_item_list_pojo.IssuedList> arrayList ) {
        this.context = context;
        this.arrayLists = arrayList;

    }
    @Override
    public Issue_bookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.edit_delte.setVisibility(View.GONE);

        Glide.with(context)
                .load(arrayLists.get(position).getImage())
                .fitCenter()
                .placeholder(R.drawable.logo)
                .into(holder.item_pic);
        holder.tital.setText(arrayLists.get(position).getName());
        holder.discraption.setText(arrayLists.get(position).getDescription());



    }


    @Override
    public int getItemCount() {
        return arrayLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_pic;
        public TextView textView,tital,discraption;
        LinearLayout edit_delte;
        public ViewHolder(View itemView) {
            super(itemView);
            edit_delte =itemView.findViewById(R.id.edit_delte);
            item_pic =itemView.findViewById(R.id.item_pic);
            tital =itemView.findViewById(R.id.tital);
            discraption =itemView.findViewById(R.id.discraption);


        }
    }
}

