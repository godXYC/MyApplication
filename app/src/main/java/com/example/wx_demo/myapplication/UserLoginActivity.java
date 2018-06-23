package com.example.wx_demo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wx_demo.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    String username;
    String password;
    EditText userNameText;
    EditText passwordText;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (UserLoginActivity.this.getCurrentFocus() != null) {
                if (UserLoginActivity.this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(UserLoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userNameText =findViewById (R.id.userName);
        passwordText = findViewById(R.id.password);



        final Button confirmButton = findViewById(R.id.confirm_btn);

        userNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeBtnText(userNameText,passwordText,confirmButton);
            }
        });

        passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                changeBtnText(userNameText,passwordText,confirmButton);
            }

        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = userNameText.getText().toString();
                password = passwordText.getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(UserLoginActivity.this,"NOT NULL!",Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            final String result= HttpUtil.sendPostMessage(params, "utf-8","/user/login");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        System.out.println(result);
                                        JSONObject jsonObject = new JSONObject(result);
                                        System.out.println(jsonObject.get("code"));
                                        if(jsonObject.get("code").equals(1)){
                                            Intent intent = new Intent(UserLoginActivity.this,PersonalCenterActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
//                                    userNameText.setText(null);
//                                    passwordText.setText(null);
                                            Toast.makeText(UserLoginActivity.this,"你恐怕是石乐志!",Toast.LENGTH_SHORT).show();
                                            System.out.println("没有登陆成功");
                                            showExitDialog();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });

                        }
                    }.start();

                }


            }
        });
    }

    public void changeBtnText(EditText editText1,EditText editText2,Button button){
        String username = editText1.getText().toString();
        String password = editText2.getText().toString();
        if(!username.equals("")&&!password.equals("")){
            button.setBackgroundColor(Color.parseColor("#ffc814"));
            button.setClickable(true);
        }
        else {
            button.setBackgroundColor(Color.parseColor("#ededed"));
            button.setClickable(false);
        }
    }

    private void showExitDialog(){
        new AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage("用户名或密码错误!")
                .setPositiveButton("确定", null)
                .show();
    }

}
