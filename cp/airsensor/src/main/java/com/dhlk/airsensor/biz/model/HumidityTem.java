package com.dhlk.airsensor.biz.model;

import java.io.Serializable;


public class HumidityTem implements Serializable {
    //传感器地址
    private String addr;
    //湿度
    private String humidity;
    //温度
    private String tem;
    //时间
    private String date;

    public HumidityTem() {
    }

    public HumidityTem(String addr, String humidity, String tem, String date) {
        this.addr = addr;
        this.humidity = humidity;
        this.tem = tem;
        this.date = date;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
