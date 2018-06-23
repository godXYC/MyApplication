package com.example.wx_demo.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wx_demo.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {


    String username;
    String password;
    EditText userNameText;
    EditText passwordText;
    private TimeCount time;
    private Button btnGetcode;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        time = new TimeCount(60000,1000);
        btnGetcode = findViewById(R.id.send_verification_code_btn);
        btnConfirm = findViewById(R.id.confirm_btn);
        userNameText = findViewById(R.id.register_userName);
        passwordText = findViewById(R.id.verification_code);
        btnGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpCode = getFourRandom();
                time.start();
                System.out.println(getFourRandom());
                new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("请输入以下验证码")
                        .setMessage("你的验证码为:"+tmpCode)
                        .setPositiveButton("确定",null)
                        .show();
                passwordText.setText(tmpCode);
            }
        });



        userNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeBtnText(userNameText,passwordText,btnConfirm);
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
                changeBtnText(userNameText,passwordText,btnConfirm);
            }

        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = userNameText.getText().toString();
                password = passwordText.getText().toString();

                if(username.equals("")||password.equals("")){
                    Toast.makeText(RegisterActivity.this,"NOT NULL!",Toast.LENGTH_SHORT).show();
                }

                else{
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            final String result= HttpUtil.sendPostMessage(params, "utf-8","/user/register");


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println(result);
                                    try {
                                        JSONObject jsonObject = new JSONObject(result);
                                        if(jsonObject.get("code").equals(1)){
                                            new AlertDialog.Builder(RegisterActivity.this)
                                                    .setTitle("SUCCESS")
                                                    .setMessage("恭喜您注册成功，即将跳转登录页面，用户名:"+username+",密码:"+password+",请牢记！")
                                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent intent = new Intent(RegisterActivity.this,UserLoginActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    })
                                                    .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //To change body of implemented methods use File | Settings | File Templates.
                                                            canCloseDialog(dialogInterface, true);//关闭对话框
                                                        }
                                                    })
                                                    .show();
                                        }
                                        else{
                                            new AlertDialog.Builder(RegisterActivity.this)
                                                    .setTitle("ERROR")
                                                    .setMessage("用户名已经存在或非法用户名!")
                                                    .setPositiveButton("确定", null)
                                                    .show();
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

    private void canCloseDialog(DialogInterface dialogInterface, boolean close) {
        try {
            Field field = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialogInterface, close);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    class TimeCount extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnGetcode.setBackgroundColor(Color.parseColor("#ededed"));
            btnConfirm.setBackgroundColor(Color.parseColor("#ffc814"));
            btnGetcode.setClickable(false);
            btnGetcode.setText(millisUntilFinished/1000 +"秒");
        }

        @Override
        public void onFinish() {
            btnGetcode.setText("重新获取");
            btnGetcode.setClickable(true);
            btnGetcode.setBackgroundColor(Color.parseColor("#ffc814"));
            btnConfirm.setBackgroundColor(Color.parseColor("#ededed"));
        }
    }

    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom ;
        }
        return fourRandom;
    }


}

