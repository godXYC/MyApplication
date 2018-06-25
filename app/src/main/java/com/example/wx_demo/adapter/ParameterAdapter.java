package com.example.wx_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wx_demo.module.ProductParameterModule;
import com.example.wx_demo.myapplication.R;

import java.util.List;

public class ParameterAdapter extends RecyclerView.Adapter<ParameterAdapter.ViewHolder>{

    private List<ProductParameterModule> productParameterModuleList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView parameterName;
        TextView parameterValue;

        public ViewHolder(View itemView) {
            super(itemView);
            parameterName = itemView.findViewById(R.id.parameterName);
            parameterValue = itemView.findViewById(R.id.parameterValue);
        }
    }
    public ParameterAdapter(List<ProductParameterModule> list){
        productParameterModuleList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_parameter_item,parent,false);
        ParameterAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductParameterModule productParameterModule = productParameterModuleList.get(position);
        holder.parameterName.setText(productParameterModule.getParameterName());
        holder.parameterValue.setText(productParameterModule.getParameterValue());
    }


    @Override
    public int getItemCount() {
        return productParameterModuleList.size();
    }
}
