package com.example.wx_demo.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.wx_demo.module.PrivilegeModule;

import java.util.ArrayList;
import java.util.List;

public class CreditGradeActivity extends AppCompatActivity {

    private List<PrivilegeModule> privilegeModuleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_grade);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initPrivilege();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_privilege);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        PrivilegeAdapter adapter = new PrivilegeAdapter(privilegeModuleList);
        recyclerView.setAdapter(adapter);


        ImageView credit_grade_close_img = findViewById(R.id.back_btn);
        credit_grade_close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPrivilege(){
        PrivilegeModule privilegeModule1 = new PrivilegeModule(R.drawable.icon_enjoy_privileges,"享受优惠","9.5折");
        PrivilegeModule privilegeModule2 = new PrivilegeModule(R.drawable.icon_shopping_rewards,"购物奖励","送信用值");
        PrivilegeModule privilegeModule3 = new PrivilegeModule(R.drawable.icon_customer_service,"客户服务","普通");
        PrivilegeModule privilegeModule4 = new PrivilegeModule(R.drawable.icon_face_recognition,"人脸识别","普通");
        PrivilegeModule privilegeModule5 = new PrivilegeModule(R.drawable.icon_member_deadline,"会员期限","永久");
        privilegeModuleList.add(privilegeModule1);
        privilegeModuleList.add(privilegeModule2);
        privilegeModuleList.add(privilegeModule3);
        privilegeModuleList.add(privilegeModule4);
        privilegeModuleList.add(privilegeModule5);
    }
}
