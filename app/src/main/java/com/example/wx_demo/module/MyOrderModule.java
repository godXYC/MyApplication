package com.example.wx_demo.module;

public class MyOrderModule {

    private String orderName;

    private String orderDate;

    private String orderPrice;

    private String date;

    public MyOrderModule(String orderName,String orderDate,String orderPrice,String date){
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.date = date;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
