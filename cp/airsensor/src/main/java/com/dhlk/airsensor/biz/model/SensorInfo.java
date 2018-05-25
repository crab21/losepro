package com.dhlk.airsensor.biz.model;

import java.io.Serializable;


public class SensorInfo implements Serializable {
    private int id;
    //可控空调短地址
    private String sensor_addr;
    //传感器地址
    private String air_addr;

    public SensorInfo() {
    }

    public SensorInfo(int id, String sensor_addr, String air_addr) {
        this.id = id;
        this.sensor_addr = sensor_addr;
        this.air_addr = air_addr;
    }

    public SensorInfo(String sensor_addr, String air_addr) {
        this.sensor_addr = sensor_addr;
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

    public String getAir_addr() {
        return air_addr;
    }

    public void setAir_addr(String air_addr) {
        this.air_addr = air_addr;
    }

}
