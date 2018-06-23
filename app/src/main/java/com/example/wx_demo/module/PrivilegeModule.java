package com.example.wx_demo.module;


/**
 * Created by apple on 2018/6/8.
 */

public class PrivilegeModule {

    private Integer privilegeImg;
    private String privilegeName;
    private String detailName;

    public PrivilegeModule(){

    }

    public PrivilegeModule(Integer privilegeImg,String privilegeName,String detailName){
        this.privilegeImg = privilegeImg;
        this.privilegeName = privilegeName;
        this.detailName = detailName;
    }

    public Integer getPrivilegeImg() {
        return privilegeImg;
    }

    public void setPrivilegeImg(Integer privilegeImg) {
        this.privilegeImg = privilegeImg;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }
}
