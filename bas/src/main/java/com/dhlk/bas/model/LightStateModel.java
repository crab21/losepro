package com.dhlk.bas.model;

/**
 * Created by fnan on 2017-11-10.
 */
public class LightStateModel {

    private int addr;
    private String addrnum;
    private float voltate; // 电压
    private float current; // 电流
    private float power; // 功率
    private float powerFactor; // 功率因数
    private int relayState; // 继电器状态
    private int environmentBrightness; // 环境亮度
    private int staffActivity; // 人员活动
    private int deviceBrightness; // 设备亮度
    private long time; // 设备状态更新时间

    @Override
    public String toString() {
        return "LightStateModel{" +
                "addr=" + addr +
                ", addrnum='" + addrnum + '\'' +
                ", voltate=" + voltate +
                ", current=" + current +
                ", power=" + power +
                ", powerFactor=" + powerFactor +
                ", relayState=" + relayState +
                ", environmentBrightness=" + environmentBrightness +
                ", staffActivity=" + staffActivity +
                ", deviceBrightness=" + deviceBrightness +
                ", time=" + time +
                '}';
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public String getAddrnum() {
        return addrnum;
    }

    public void setAddrnum(String addrnum) {
        this.addrnum = addrnum;
    }

    public float getVoltate() {
        return voltate;
    }

    public void setVoltate(float voltate) {
        this.voltate = voltate;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(float powerFactor) {
        this.powerFactor = powerFactor;
    }

    public int getRelayState() {
        return relayState;
    }

    public void setRelayState(int relayState) {
        this.relayState = relayState;
    }

    public int getEnvironmentBrightness() {
        return environmentBrightness;
    }

    public void setEnvironmentBrightness(int environmentBrightness) {
        this.environmentBrightness = environmentBrightness;
    }

    public int getStaffActivity() {
        return staffActivity;
    }

    public void setStaffActivity(int staffActivity) {
        this.staffActivity = staffActivity;
    }

    public int getDeviceBrightness() {
        return deviceBrightness;
    }

    public void setDeviceBrightness(int deviceBrightness) {
        this.deviceBrightness = deviceBrightness;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
