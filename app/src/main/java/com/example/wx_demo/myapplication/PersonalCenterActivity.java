package com.example.wx_demo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PersonalCenterActivity extends AppCompatActivity {


    TextView nickName;
    TextView userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_center);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        nickName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        Intent intent = getIntent();

        nickName.setText(intent.getStringExtra("userName"));
        userPhone.setText(intent.getStringExtra("userPhone"));

        View include1 = findViewById(R.id.include1);
        View include2 = findViewById(R.id.include2);
        View include3 = findViewById(R.id.include3);
//        TextView returnText = findViewById(R.id.title_button);
        include1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this,MyOrderActivity.class);
                startActivity(intent);
            }
        });

        include2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this,FaceActivity.class);
                startActivity(intent);
            }
        });

        include3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this,CreditGradeActivity.class);
                startActivity(intent);
            }
        });
//        returnText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_first:
                Toast.makeText(this,"点了没用",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_second:
                Toast.makeText(this,"点了还是没用",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_third:
                Toast.makeText(this,"点了就是没用",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_fourth:
                Toast.makeText(this,"都说了没用你还点什么点",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }



}
