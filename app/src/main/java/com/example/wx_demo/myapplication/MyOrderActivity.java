package com.example.wx_demo.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wx_demo.module.MyOrderBriefModule;
import com.example.wx_demo.module.MyOrderModule;
import com.example.wx_demo.module.MyOrderMonthModule;
import com.example.wx_demo.util.HttpUtil;
import com.example.wx_demo.util.TimedDisposeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends AppCompatActivity {


//    private List<MyOrderMonthModule> myOrderMonthModuleList = new ArrayList<>();
//    private List<MyOrderBriefModule> myOrderBriefModuleList = new ArrayList<>();
    private List<MyOrderModule> myOrderModuleList = new ArrayList<>();
    MyOrderAdapter myOrderAdapter;
    RelativeLayout month_relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

//            initMyOrderMonth();
//            initMyOrderBrief();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    initMyOrder();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();
//            RecyclerView month_recycler_view = findViewById(R.id.month_recycler_view);
//            RecyclerView good_recycler_view = findViewById(R.id.good_recycler_view);
            RecyclerView order_recycler_view = findViewById(R.id.order_recycler_view);
//            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
//            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//            month_recycler_view.setLayoutManager(linearLayoutManager1);
//            good_recycler_view.setLayoutManager(linearLayoutManager2);
            order_recycler_view.setLayoutManager(linearLayoutManager);
//            MyOrderMonthAdapter myOrderMonthAdapter = new MyOrderMonthAdapter(myOrderMonthModuleList);
//            MyOrderBriefAdapter myOrderBriefAdapter = new MyOrderBriefAdapter(myOrderBriefModuleList);
            myOrderAdapter = new MyOrderAdapter(myOrderModuleList);
//            month_recycler_view.setAdapter(myOrderMonthAdapter);
//            good_recycler_view.setAdapter(myOrderBriefAdapter);
            order_recycler_view.setAdapter(myOrderAdapter);

            //点击recycler事件
            myOrderAdapter.setOnItemClickListener(new MyOrderBriefAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(MyOrderActivity.this,OrderListDetailActivity.class);
                    intent.putExtra("sn","20185180001");//向下一个活动传递数据！
                    Toast.makeText(MyOrderActivity.this, "click " + myOrderModuleList.get(position), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            });

        ImageView close_img = findViewById(R.id.order_close_img);
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

//    public void initMyOrderMonth(){
//
//            MyOrderMonthModule myOrderMonthModule1 = new MyOrderMonthModule("本月");
//            myOrderMonthModuleList.add(myOrderMonthModule1);
//
//    }
//
//    public void initMyOrderBrief(){
//        MyOrderBriefModule myOrderBriefModule1 = new MyOrderBriefModule("农夫山泉矿泉水一达通1","3-12 12:39","￥12.00");
//        MyOrderBriefModule myOrderBriefModule2 = new MyOrderBriefModule("农夫山泉矿泉水一达通1","3-12 12:39","￥12.00");
//        MyOrderBriefModule myOrderBriefModule3 = new MyOrderBriefModule("农夫山泉矿泉水一达通1","3-12 12:39","￥12.00");
//        myOrderBriefModuleList.add(myOrderBriefModule1);
//        myOrderBriefModuleList.add(myOrderBriefModule2);
//        myOrderBriefModuleList.add(myOrderBriefModule3);
//    }
    public void initMyOrder() throws JSONException {

        month_relativeLayout = findViewById(R.id.month_relative_layout);


        Map<String, String> params = new HashMap<String, String>();
        params.put("ognNumber", "");
        String result= HttpUtil.sendPostMessage(params, "utf-8","/get/orderList");

        System.out.println(new JSONObject(result).get("data"));
        final JSONArray jsonArray = new JSONObject(result).getJSONArray("data");
        System.out.println("jsonObject:"+jsonArray);



        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                for(int i = 0 ; i < jsonArray.length(); i++){

                    try {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        Calendar calendar = TimedDisposeUtil.timeToSpecified(TimedDisposeUtil.timestampToFormatTime(jsonObject.get("createTime").toString()));

                        MyOrderModule myOrderModule = new MyOrderModule(jsonObject.get("boxName").toString(),
                                TimedDisposeUtil.timestampToFormatTime(jsonObject.get("createTime").toString()),
                                "￥"+ new DecimalFormat("0.00").format(jsonObject.get("totalPrice")),//将(数字)转化为(数字.00)显示
                                (calendar.get(Calendar.MONTH)+1)+"月");



                        myOrderModuleList.add(myOrderModule);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                myOrderAdapter.notifyDataSetChanged();
            }
        });

    }


}
