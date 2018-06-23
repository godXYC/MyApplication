package com.example.wx_demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wx_demo.module.OrderDetailModule;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

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


    List<OrderDetailModule> orderDetailModuleList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pictureUrl;
        TextView goodName;
        TextView currentPrice;
        TextView originalPrice;
        TextView goodNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            pictureUrl = itemView.findViewById(R.id.good_img);
            goodName = itemView.findViewById(R.id.good_message);
            currentPrice = itemView.findViewById(R.id.current_price);
            originalPrice = itemView.findViewById(R.id.original_price);
            goodNumber = itemView.findViewById(R.id.good_number);

        }
    }

    public OrderDetailAdapter(List<OrderDetailModule> list){
        orderDetailModuleList = list;
    }

    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item,parent,false);
        OrderDetailAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final OrderDetailAdapter.ViewHolder holder, int position) {
        OrderDetailModule orderDetailModule = orderDetailModuleList.get(position);
        holder.pictureUrl.setImageResource(orderDetailModule.getPictureUrl());
        holder.goodNumber.setText("X"+orderDetailModule.getGoodNumber());
        holder.goodName.setText(orderDetailModule.getGoodName());
        holder.currentPrice.setText(orderDetailModule.getCurrentPrice());
        holder.originalPrice.setText(orderDetailModule.getOriginalPrice());

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
        return orderDetailModuleList.size();
    }

    public OrderDetailModule getItem(int pos){
        if(orderDetailModuleList != null && orderDetailModuleList.size() > pos){
            return orderDetailModuleList.get(pos);
        }else {
            return null;
        }
    }
}
