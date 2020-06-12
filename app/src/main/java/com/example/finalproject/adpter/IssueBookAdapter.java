package com.example.finalproject.adpter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class IssueBookAdapter extends RecyclerView.Adapter<IssueBookAdapter.ViewHolder>{

    Context context;

    // RecyclerView recyclerView;
    public IssueBookAdapter(Context context) {
        this.context = context;

    }
    @Override
    public IssueBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.edit_delte.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        LinearLayout edit_delte;
        public ViewHolder(View itemView) {
            super(itemView);
            edit_delte =itemView.findViewById(R.id.edit_delte);


        }
    }
}


