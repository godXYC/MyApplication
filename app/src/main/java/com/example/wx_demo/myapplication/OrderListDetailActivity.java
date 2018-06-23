package com.example.wx_demo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wx_demo.module.OrderDetailModule;
import com.example.wx_demo.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class OrderListDetailActivity extends AppCompatActivity {

    List<String> ogn_number = new ArrayList<String>();
    private List<OrderDetailModule> orderDetailModuleList = new ArrayList<>();
    OrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    initGoodDetail();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        RecyclerView good_detail_recycler = findViewById(R.id.order_detail_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        good_detail_recycler.setLayoutManager(linearLayoutManager);
        orderDetailAdapter = new OrderDetailAdapter(orderDetailModuleList);
        good_detail_recycler.setAdapter(orderDetailAdapter);

        //点击recycler事件
        orderDetailAdapter.setOnItemClickListener(new MyOrderBriefAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(OrderListDetailActivity.this,GoodDetailActivity.class);
                intent.putExtra("ogn_number", orderDetailAdapter.getItem(position).getOgnNumber());
                Toast.makeText(OrderListDetailActivity.this, "click " + orderDetailModuleList.get(position), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        });

        ImageView good_detail_close_img = findViewById(R.id.close_img);
        good_detail_close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initGoodDetail() throws JSONException {

        Intent intent = getIntent();
        String sn = intent.getStringExtra("sn");
        Map<String, String> params = new HashMap<String, String>();
        System.out.println(sn);
        params.put("orderId", sn);
        String result= HttpUtil.sendPostMessage(params, "utf-8","/get/orderDetail");

        System.out.println(new JSONObject(result));
        System.out.println(new JSONObject(result).get("data"));

        final JSONArray jsonArray = new JSONObject(result).getJSONArray("data");
        System.out.println("jsonArray:"+jsonArray);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < jsonArray.length();i++){
                    OrderDetailModule orderDetailModule = null;
                    String ognNumber = "";
                    try {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        orderDetailModule = new OrderDetailModule(jsonObject.get("name").toString(),
                                "￥"+jsonObject.get("sellPrice").toString(),
                                "￥"+jsonObject.get("payPrice").toString(),
                                Integer.parseInt(String.valueOf(jsonObject.getInt("number"))),
                                R.drawable.goods_img_1);
                        orderDetailModule.setOgnNumber(jsonObject.get("ognNumber").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    orderDetailModuleList.add(orderDetailModule);
                }
                orderDetailAdapter.notifyDataSetChanged();
            }
        });




    }
}
