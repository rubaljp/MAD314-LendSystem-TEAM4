package com.example.finalproject.adpter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.GlobalClass;
import com.example.finalproject.R;
import com.example.finalproject.admin.Edit_item;
import com.example.finalproject.interface_api.ApiClient;
import com.example.finalproject.pojo_class.Add_item_pojo;
import com.example.finalproject.pojo_class.AdminItems_list_pojo;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageItem extends RecyclerView.Adapter<ManageItem.ViewHolder> implements Filterable {

    Context context;
    ArrayList<AdminItems_list_pojo.ItemList> arrayList;
    ArrayList<AdminItems_list_pojo.ItemList> filter;
    // RecyclerView recyclerView;
    public ManageItem(Context context, ArrayList<AdminItems_list_pojo.ItemList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.filter = arrayList;

    }
    @Override
    public ManageItem.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.listitems_row, parent, false);
        ManageItem.ViewHolder viewHolder = new ManageItem.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ManageItem.ViewHolder holder, final int position) {
        if (arrayList.get(position).getType() == 1){
            holder.elect.setText("Electronics");
        }else {
            holder.elect.setText("Book");
        }


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
                context.startActivity(new Intent(context, Edit_item.class).putExtra("tital",filter.get(position).getName())
                        .putExtra("total_item",filter.get(position).getNoOfItems()).putExtra("discraption",filter.get(position).getDescription())
                        .putExtra("itemid",filter.get(position).getId()).putExtra("edit","edit").
                                putExtra("item_type",filter.get(position).getType())
                        .putExtra("image",filter.get(position).getImage()));
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody session_id = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"),"");
                RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                RequestBody no_of_items = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"),"2");
                RequestBody item_id = RequestBody.create(MediaType.parse("multipart/form-data"),""+arrayList.get(position).getId());
                RequestBody item_type = RequestBody.create(MediaType.parse("multipart/form-data"),"");

                update_del_items(context,session_id,name,description,no_of_items, type,item_id,item_type,null,position);            }
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
        public TextView tital,discraption,edit,delete,elect;
        public ViewHolder(View itemView) {
            super(itemView);

            item_pic=itemView.findViewById(R.id.item_pic);
            tital=itemView.findViewById(R.id.tital);
            discraption=itemView.findViewById(R.id.discraption);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            elect=itemView.findViewById(R.id.elect);

        }
    }


    public void update_del_items(final Context context, RequestBody session_id,
                                 RequestBody name,
                                 RequestBody description,
                                 RequestBody no_of_items,
                                 RequestBody type ,
                                 RequestBody item_id ,
                                 RequestBody item_type ,
                                 MultipartBody.Part image, final int postion) {
        //dailogshow(context);
        Call<Add_item_pojo> userpost_responseCall = ApiClient.getClient().update_del_items(session_id,name,description,no_of_items,type,item_id,item_type,image);
        userpost_responseCall.enqueue(new Callback<Add_item_pojo>() {
            @Override
            public void onResponse(Call<Add_item_pojo> call, Response<Add_item_pojo> response) {
                // dailoghide(context);

                if (response.body().getStatus() ==  200) {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
                    removeAt(postion);
                } else {
                    GlobalClass.showtost(context, "" + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Add_item_pojo> call, Throwable t) {

                //  dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });


    }
    public void removeAt(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrayList.size());
    }
}

