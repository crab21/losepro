package com.dhlk.airsensor.biz.model;

import java.io.Serializable;


public class SenInfo implements Serializable {
    private int id;
    //传感器地址
    private String sensor_addr;
    //和传感器比较的地址
    private String sensor_tem;
    //设定的起始时间
    private String air_tem;
    private String start_date;
    private String end_date;
    private String air_addr;

    public SenInfo() {
    }

    public SenInfo(String sensor_addr, String sensor_tem, String air_tem, String start_date, String end_date) {
        this.sensor_addr = sensor_addr;
        this.sensor_tem = sensor_tem;
        this.air_tem = air_tem;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public SenInfo(int id, String sensor_addr, String sensor_tem, String air_tem, String start_date, String end_date, String air_addr) {
        this.id = id;
        this.sensor_addr = sensor_addr;
        this.sensor_tem = sensor_tem;
        this.air_tem = air_tem;
        this.start_date = start_date;
        this.end_date = end_date;
        this.air_addr = air_addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensor_addr() {
        return sensor_addr;
    }

    public void setSensor_addr(String sensor_addr) {
        this.sensor_addr = sensor_addr;
    }

    public String getSensor_tem() {
        return sensor_tem;
    }

    public void setSensor_tem(String sensor_tem) {
        this.sensor_tem = sensor_tem;
    }

    public String getAir_tem() {
        return air_tem;
    }

    public void setAir_tem(String air_tem) {
        this.air_tem = air_tem;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getAir_addr() {
        return air_addr;
    }

    public void setAir_addr(String air_addr) {
        this.air_addr = air_addr;
    }
}
