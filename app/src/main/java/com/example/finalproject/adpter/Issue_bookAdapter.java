package com.example.finalproject.adpter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.GlobalClass;
import com.example.finalproject.R;
import com.example.finalproject.interface_api.CSPreferences;
import com.example.finalproject.interface_api.WebApicall;
import com.example.finalproject.pojo_class.Issued_item_list_pojo;
import com.example.finalproject.user.Show_item_detail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Issue_bookAdapter extends RecyclerView.Adapter<Issue_bookAdapter.ViewHolder>{

    Context context;
    ArrayList<Issued_item_list_pojo.IssuedList> arrayLists;
    RecyclerView recyclerView;
    // RecyclerView recyclerView;
    public Issue_bookAdapter(Context context, ArrayList<Issued_item_list_pojo.IssuedList> arrayList,RecyclerView recyclerView ) {
        this.context = context;
        this.arrayLists = arrayList;
        this.recyclerView = recyclerView;

    }
    @Override
    public Issue_bookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.user_itemrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


       holder.avaliable.setVisibility(View.GONE);
        holder.ret_date.setVisibility(View.VISIBLE);
        holder.ret_date.setText("Return date : "+arrayLists.get(position).getReturnDate());


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date   strDate = sdf.parse(arrayLists.get(position).getReturnDate());
            if (new Date().after(strDate)) {
                holder.fine.setVisibility(View.VISIBLE);
                holder.fine.setText("please pay $20 to clerk for late sumbission of item");

            }else {
                Log.d("dfsfsdf","NO");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if (arrayLists.get(position).getReturnstatus() == 1){
            holder.retrun.setVisibility(View.GONE);
       }else {
            holder.retrun.setVisibility(View.VISIBLE);

       }

        Glide.with(context)
                .load(arrayLists.get(position).getImage())
                .fitCenter()
                .placeholder(R.drawable.logo)
                .into(holder.item_pic);
        holder.tital.setText(arrayLists.get(position).getName());
        holder.discraption.setText(arrayLists.get(position).getDescription());

holder.retrun.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (GlobalClass.isNetworkConnected(context)) {
            WebApicall webApicall =new WebApicall();
            webApicall.return_issued_item(context, CSPreferences.readString(context,"sessioniid"),
                    ""+arrayLists.get(position).getId(),recyclerView,position,arrayLists);

        } else {

            Toast.makeText(context, R.string.nointernet, Toast.LENGTH_LONG).show();

        }

    }
});

    }


    @Override
    public int getItemCount() {
        return arrayLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_pic;
        public TextView textView,tital,discraption;
        TextView avaliable,retrun,ret_date,fine;
        public ViewHolder(View itemView) {
            super(itemView);
            avaliable =itemView.findViewById(R.id.avaliable);
            retrun =itemView.findViewById(R.id.retrun);
            item_pic =itemView.findViewById(R.id.item_pic);
            tital =itemView.findViewById(R.id.tital);
            discraption =itemView.findViewById(R.id.discraption);
            ret_date =itemView.findViewById(R.id.ret_date);
            fine =itemView.findViewById(R.id.fine);


        }
    }
}


