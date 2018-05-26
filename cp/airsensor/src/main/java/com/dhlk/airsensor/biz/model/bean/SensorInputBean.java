package com.dhlk.airsensor.biz.model.bean;

/**
 * 请求bean
 */
public class SensorInputBean {
    private String addr;
    private String tem;
    private String date;
    private String air_addr;

    public SensorInputBean() {
    }

    public SensorInputBean(String addr, String tem, String date, String air_addr) {
        this.addr = addr;
        this.tem = tem;
        this.date = date;
        this.air_addr = air_addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getAir_addr() {
        return air_addr;
    }

    public void setAir_addr(String air_addr) {
        this.air_addr = air_addr;
    }
}
