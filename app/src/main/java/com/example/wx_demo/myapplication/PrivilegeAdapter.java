package com.example.wx_demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wx_demo.module.PrivilegeModule;

import java.util.List;

/**
 * Created by apple on 2018/6/8.
 */

public class PrivilegeAdapter extends RecyclerView.Adapter<PrivilegeAdapter.ViewHolder> {

    private List<PrivilegeModule> privilegeModuleList;


    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView privilegeImg;
        TextView privilegeName;
        TextView detailName;

        public ViewHolder(View itemView) {
            super(itemView);
            privilegeImg = itemView.findViewById(R.id.icon_enjoy_privileges);
            privilegeName = itemView.findViewById(R.id.privilege_name);
            detailName = itemView.findViewById(R.id.privilege_detail_name);

        }
    }
    public PrivilegeAdapter(List<PrivilegeModule> list){
        privilegeModuleList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.privilege_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PrivilegeModule privilegeModule = privilegeModuleList.get(position);
        holder.privilegeImg.setImageResource(privilegeModule.getPrivilegeImg());
        holder.privilegeName.setText(privilegeModule.getPrivilegeName());
        holder.detailName.setText(privilegeModule.getDetailName());
    }

    @Override
    public int getItemCount() {
        return privilegeModuleList.size();
    }


}
