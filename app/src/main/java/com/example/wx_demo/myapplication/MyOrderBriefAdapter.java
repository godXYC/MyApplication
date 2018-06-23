package com.example.wx_demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wx_demo.module.MyOrderBriefModule;

import java.util.List;

public class MyOrderBriefAdapter extends RecyclerView.Adapter<MyOrderBriefAdapter.ViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    private List<MyOrderBriefModule> myOrderBriefModuleList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderName;
        TextView orderDate;
        TextView orderPrice;
        LinearLayout view_tmp;

        public ViewHolder(View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.my_order_name);
            orderDate = itemView.findViewById(R.id.my_order_date);
            orderPrice = itemView.findViewById(R.id.my_order_price);
            view_tmp = itemView.findViewById(R.id.my_order_view);
        }
    }
    public MyOrderBriefAdapter(List<MyOrderBriefModule> list){
        myOrderBriefModuleList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_brief_item,parent,false);
        MyOrderBriefAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MyOrderBriefModule myOrderBriefModule = myOrderBriefModuleList.get(position);
        holder.orderName.setText(myOrderBriefModule.getOrderName());
        holder.orderDate.setText(myOrderBriefModule.getOrderDate());
        holder.orderPrice.setText(myOrderBriefModule.getOrderPrice());
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                ViewHolder hold = holder;
                @Override
                public void onClick(View v) {
                    int position = hold.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(hold.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
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
        return myOrderBriefModuleList.size();
    }
}
