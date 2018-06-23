package com.example.wx_demo.module;

public class MyOrderMonthModule {
    private String date;

    public  MyOrderMonthModule(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
