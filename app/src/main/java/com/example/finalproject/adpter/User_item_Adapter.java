package com.example.finalproject.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class User_item_Adapter extends RecyclerView.Adapter<User_item_Adapter.ViewHolder> implements Filterable {

    Context context;
    ArrayList<AdminItems_list_pojo.ItemList> arrayList;
    ArrayList<AdminItems_list_pojo.ItemList> filter;
    // RecyclerView recyclerView;
    public User_item_Adapter(Context context, ArrayList<AdminItems_list_pojo.ItemList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.filter = arrayList;

    }
    @Override
    public User_item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        User_item_Adapter.ViewHolder viewHolder = new User_item_Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(User_item_Adapter.ViewHolder holder, final int position) {

        Glide.with(context)
                .load(filter.get(position).getImage())
                .fitCenter()
                .placeholder(R.drawable.logo)
                .into(holder.item_pic);
        holder.tital.setText(filter.get(position).getName());
        holder.discraption.setText(filter.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, Show_item_detail.class).putExtra("tital",filter.get(position).getName())
                        .putExtra("total_item",filter.get(position).getNoOfItems()).putExtra("discraption",filter.get(position).getDescription())
                        .putExtra("itemid",filter.get(position).getId()).putExtra("edit","edit").
                                putExtra("item_type",filter.get(position).getType())
                        .putExtra("image",filter.get(position).getImage()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return filter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filter = arrayList;
                } else {
                    ArrayList<AdminItems_list_pojo.ItemList> filteredList = new ArrayList<>();
                    for (AdminItems_list_pojo.ItemList row : arrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filter = (ArrayList<AdminItems_list_pojo.ItemList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_pic;
        public TextView tital,discraption,edit,delete;
        public ViewHolder(View itemView) {
            super(itemView);

            item_pic=itemView.findViewById(R.id.item_pic);
            tital=itemView.findViewById(R.id.tital);
            discraption=itemView.findViewById(R.id.discraption);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}