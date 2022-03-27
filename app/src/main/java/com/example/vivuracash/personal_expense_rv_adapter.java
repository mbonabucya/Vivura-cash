package com.example.vivuracash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class personal_expense_rv_adapter extends RecyclerView.Adapter<personal_expense_rv_adapter.ViewHolder> {
    private List<personal_expenses_Model> MY_expenses_list;
    private Context ctx;

    public personal_expense_rv_adapter(List<personal_expenses_Model> MY_expenses_list,Context ctx){

        this.MY_expenses_list =MY_expenses_list;
        this.ctx=ctx;
    }
    public void filterList(ArrayList<personal_expenses_Model> filterllist) {
        MY_expenses_list = filterllist;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public personal_expense_rv_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_expenses_rv_layout,parent,false);
        personal_expense_rv_adapter.ViewHolder holder= new personal_expense_rv_adapter.ViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull personal_expense_rv_adapter.ViewHolder holder, int position) {
        String Amount =MY_expenses_list.get(position).getAmount();
        String desc =MY_expenses_list.get(position).getDescription();
        String Itali=MY_expenses_list.get(position).getItaliki();
        String activity=MY_expenses_list.get(position).getActivity();
        holder.setData(Amount,activity,Itali,desc);
    }

    @Override
    public int getItemCount() {
        return MY_expenses_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView amount_out, descr,outdate,activity_out;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount_out=itemView.findViewById(R.id.Amount_out);
            descr=itemView.findViewById(R.id.desc_out);
            outdate=itemView.findViewById(R.id.date_out);
            activity_out=itemView.findViewById(R.id.activity_out);
        }

        public void setData(String amount, String desc, String itali, String activity) {
            amount_out.setText(amount);
            descr.setText(desc);
            outdate.setText(itali);
            activity_out.setText(activity);


        }
    }
}
