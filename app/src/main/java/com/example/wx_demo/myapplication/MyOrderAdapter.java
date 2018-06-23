package com.example.wx_demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wx_demo.module.MyOrderModule;
import com.example.wx_demo.util.TimedDisposeUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private MyOrderBriefAdapter.OnItemClickListener mOnItemClickListener;
    private MyOrderBriefAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(MyOrderBriefAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(MyOrderBriefAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    private List<MyOrderModule> myOrderModuleList = new ArrayList<>();
    final List<String> tmpList = new ArrayList<String>();

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView orderName;
        TextView orderDate;
        TextView orderPrice;
        TextView orderMonthDate;
        RelativeLayout view_tmp;
        RelativeLayout month_relative_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            orderName = itemView.findViewById(R.id.my_order_name);
            orderDate = itemView.findViewById(R.id.my_order_date);
            orderPrice = itemView.findViewById(R.id.my_order_price);
            orderMonthDate = itemView.findViewById(R.id.monthName);
            view_tmp = itemView.findViewById(R.id.my_order_relativeLayout);
            month_relative_layout = itemView.findViewById(R.id.month_relative_layout);
        }

    }
    public MyOrderAdapter(List<MyOrderModule> list){
        this.myOrderModuleList = list;
    }


    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_month_item,parent,false);
        MyOrderAdapter.ViewHolder holder = new MyOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyOrderAdapter.ViewHolder holder, int position) {
        MyOrderModule myOrderModule = myOrderModuleList.get(position);
        holder.orderMonthDate.setText(myOrderModule.getDate());
        holder.orderDate.setText(myOrderModule.getOrderDate());
        holder.orderName.setText(myOrderModule.getOrderName());
        holder.orderPrice.setText(myOrderModule.getOrderPrice());

        Calendar calendar =  TimedDisposeUtil.timeToSpecified(TimedDisposeUtil.timestampToFormatTime(TimedDisposeUtil.dateTimeStamp(myOrderModule.getOrderDate(),"yyyy-MM-dd HH:mm:ss")));


        for(String str : tmpList){
            Log.e("tag",str);
        }

        if(tmpList.size()==0){
            tmpList.add(String.valueOf(calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)));
            holder.month_relative_layout.setVisibility(View.VISIBLE);
        }
        else{
            for(String tmp:tmpList){
                if(!tmp.equals(String.valueOf(calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)))){
                    tmpList.add(String.valueOf(calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)));
                    holder.month_relative_layout.setVisibility(View.VISIBLE);
                }
                else{
                    holder.month_relative_layout.setVisibility(View.GONE);
                    break;
                }
            }

//            if(isTrue==true){
//
//            }
//            else {
//            }
        }




        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.view_tmp.setOnClickListener(new View.OnClickListener() {
               ViewHolder hold = holder;
                @Override
                public void onClick(View v) {
                    int position = hold.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(hold.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.view_tmp.setOnLongClickListener(new View.OnLongClickListener() {
               ViewHolder hold = holder;
                @Override
                public boolean onLongClick(View v) {
                    int position = hold.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(hold.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return myOrderModuleList.size();
    }


}
