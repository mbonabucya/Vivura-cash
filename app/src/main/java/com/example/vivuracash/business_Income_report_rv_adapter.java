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

public class business_Income_report_rv_adapter extends RecyclerView.Adapter<business_Income_report_rv_adapter.ViewHolder> {
    private List<Income_Business_Model> MY_Income_list;
    private Context ctx;


    public business_Income_report_rv_adapter(List<Income_Business_Model> MY_Income_list, Context ctx){
        this.MY_Income_list=MY_Income_list;
        this.ctx=ctx;
    }

    public void filterList(ArrayList<Income_Business_Model> filterllist) {
        MY_Income_list = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public business_Income_report_rv_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_income_rv__layout,parent,false);
        ViewHolder holder= new ViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull business_Income_report_rv_adapter.ViewHolder holder, int position) {

        String Amount =MY_Income_list.get(position).getAmount();
        String reason =MY_Income_list.get(position).getReason();
        String Itali=MY_Income_list.get(position).getItaliki();
        String item=MY_Income_list.get(position).getItem();
        holder.setData(Amount,reason,Itali,item);

    }

    @Override
    public int getItemCount() {
        return MY_Income_list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView AmountTV, reasonTV, italiki,ite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            AmountTV=itemView.findViewById(R.id.idTVAmount);
            reasonTV=itemView.findViewById(R.id.idTVReason);
            italiki=itemView.findViewById(R.id.date_auto);
            ite=itemView.findViewById(R.id.item);


        }
        public void setData( String amount, String reason, String date,String item ) {

            AmountTV.setText(amount);
            reasonTV.setText(reason);
            italiki.setText(date);
            ite.setText(item);
        }
    }
}
