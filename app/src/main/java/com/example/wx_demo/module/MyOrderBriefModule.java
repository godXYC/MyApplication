package com.example.wx_demo.module;

public class MyOrderBriefModule {

    private String orderName;

    private String orderDate;

    private String orderPrice;

    public MyOrderBriefModule(String orderName,String orderDate,String orderPrice){
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
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
}
