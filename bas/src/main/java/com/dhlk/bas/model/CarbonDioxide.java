package com.dhlk.bas.model;

/**
 * Created by fnan on 2017-12-28.
 * 二氧化碳、温湿度传感器
 */
public class CarbonDioxide {

    private float temperature; // 温度
    private float humidity; // 湿度
    private int carbon_dioxide_concentration; // 二氧化碳浓度

    @Override
    public String toString() {
        return "CarbonDioxide{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", carbon_dioxide_concentration=" + carbon_dioxide_concentration +
                '}';
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getCarbon_dioxide_concentration() {
        return carbon_dioxide_concentration;
    }

    public void setCarbon_dioxide_concentration(int carbon_dioxide_concentration) {
        this.carbon_dioxide_concentration = carbon_dioxide_concentration;
    }
}
