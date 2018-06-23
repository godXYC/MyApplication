package com.example.wx_demo.module;

public class OrderDetailModule {

    private String goodName;
    private String currentPrice;
    private String originalPrice;
    private int goodNumber;
    private int pictureUrl;
    private String ognNumber;


    public OrderDetailModule(){

    }

    public OrderDetailModule(String goodName,String currentPrice,String originalPrice,int goodNumber,int pictureUrl){
        this.goodName = goodName;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
        this.goodNumber = goodNumber;
        this.pictureUrl = pictureUrl;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    public int getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(int pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getOgnNumber() {
        return ognNumber;
    }

    public void setOgnNumber(String ognNumber) {
        this.ognNumber = ognNumber;
    }
}
