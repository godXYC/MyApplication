package com.example.wx_demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wx_demo.module.MyOrderMonthModule;

import java.util.List;

public class MyOrderMonthAdapter extends RecyclerView.Adapter<MyOrderMonthAdapter.ViewHolder> {

    private List<MyOrderMonthModule> myOrderMonthAdapterList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderDate;

        public ViewHolder(View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.monthName);
        }
    }
    public MyOrderMonthAdapter(List<MyOrderMonthModule> list){
        myOrderMonthAdapterList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_brief_month_item,parent,false);
        MyOrderMonthAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyOrderMonthModule myOrderMonthModule = myOrderMonthAdapterList.get(position);
        holder.orderDate.setText(myOrderMonthModule.getDate());

    }


    @Override
    public int getItemCount() {
        return myOrderMonthAdapterList.size();
    }
}
