package com.example.wx_demo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wx_demo.module.ProductParameterModule;
import com.example.wx_demo.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodDetailActivity extends AppCompatActivity {

    ImageView goodImgView;
    TextView goodName;
    TextView goodChangePrice;
    TextView goodOriginalPrice;
    ParameterAdapter adapter;
    private List<ProductParameterModule> productParameterModuleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    initParameter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        RecyclerView recyclerView = findViewById(R.id.good_recyclerView);
        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(this);


        fullyLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fullyLinearLayoutManager.setSmoothScrollbarEnabled(true);

        recyclerView.setLayoutManager(fullyLinearLayoutManager);
        adapter = new ParameterAdapter(productParameterModuleList);
        recyclerView.setAdapter(adapter);

        ImageView good_detail_close_img = findViewById(R.id.back_btn);
        good_detail_close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initParameter() throws JSONException {

        Intent intent = getIntent();
        String ognNumber = intent.getStringExtra("ogn_number");

        Map<String, String> params = new HashMap<String, String>();
        params.put("ognNumber", ognNumber);
        String result= HttpUtil.sendPostMessage(params, "utf-8","/get/good");

        System.out.println(new JSONObject(result));
        System.out.println(new JSONObject(result).get("data"));

        final JSONObject jsonObject = new JSONObject(result).getJSONObject("data");

        System.out.println("jsonObject:"+jsonObject);

        goodImgView = findViewById(R.id.goods_img);
        goodName = findViewById(R.id.good_name);
        goodChangePrice = findViewById(R.id.change_price);
        goodOriginalPrice = findViewById(R.id.original_price);



        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                    try {

                        goodImgView.setImageResource(R.drawable.img_big);
                        goodName.setText(jsonObject.get("goodName").toString());
                        goodChangePrice.setText(jsonObject.get("sellPrice").toString());
                        goodOriginalPrice.setText(jsonObject.get("originalPrice").toString());

                        ProductParameterModule productParameterModule1 = new ProductParameterModule("品牌", jsonObject.get("goodBrand").toString());
                        ProductParameterModule productParameterModule2 = new ProductParameterModule("净含量", jsonObject.get("specification").toString());
                        ProductParameterModule productParameterModule3 = new ProductParameterModule("系列", jsonObject.get("series").toString());
                        ProductParameterModule productParameterModule4 = new ProductParameterModule("生产日期", "2017.9.30");
                        ProductParameterModule productParameterModule5 = new ProductParameterModule("保质期", jsonObject.get("warrantyPeriod").toString());
                        ProductParameterModule productParameterModule6 = new ProductParameterModule("产地", jsonObject.get("address").toString());
                        ProductParameterModule productParameterModule7 = new ProductParameterModule("储藏方式", jsonObject.get("storage").toString());
                        productParameterModuleList.add(productParameterModule1);
                        productParameterModuleList.add(productParameterModule2);
                        productParameterModuleList.add(productParameterModule3);
                        productParameterModuleList.add(productParameterModule4);
                        productParameterModuleList.add(productParameterModule5);
                        productParameterModuleList.add(productParameterModule6);
                        productParameterModuleList.add(productParameterModule7);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                adapter.notifyDataSetChanged();
            }
        });

    }
}
