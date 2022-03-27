package com.example.vivuracash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class businessAdapter extends RecyclerView.Adapter<businessAdapter.ViewHolder> {

    private List<businessModel>MybusinessList;
    private Context ctx;


    public businessAdapter(List<businessModel>MybusinessList,Context ctx){
        this.MybusinessList= MybusinessList;
        this.ctx=ctx;

    }

    @NonNull
    @Override
    public businessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item_layout,parent,false);
        ViewHolder holder= new ViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull businessAdapter.ViewHolder holder, int position) {
        int resource = MybusinessList.get(position).getLogo();
        String businessName= MybusinessList.get(position).getBusiness_name_text();
        String businessContact =MybusinessList.get(position).getBusiness_contact_text();
        holder.setData(resource,businessName,businessContact);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ctx,businessDetails.class) ;
                intent.putExtra("logo",resource);
                intent.putExtra("Business_name",businessName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });

        holder.options_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup =new PopupMenu(ctx,holder.options_menu);
                popup.inflate(R.menu.options_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return MybusinessList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private ImageView my_logo;
        private TextView name,contact,options_menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            my_logo =itemView.findViewById(R.id.logo);
            name= itemView.findViewById(R.id.business_name_text);
            contact=itemView.findViewById(R.id.business_contact_text);
            options_menu=itemView.findViewById(R.id.textViewOptions);

        }

        public void setData(int resource, String businessName, String businessContact) {

            my_logo.setImageResource(resource);
            name.setText(businessName);
            contact.setText(businessContact);
        }
    }
}





































