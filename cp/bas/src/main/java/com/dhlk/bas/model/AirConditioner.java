package com.dhlk.bas.model;

import java.util.Date;

/**
 * Created by fnan on 2017-11-29.
 * 空调实时数据信息
 */

public class AirConditioner {

    private int addr; // 空调短地址
    private String addrnum; // 空调段号地址号
    private int switch0; // 开关机：1-开，0-关
    private int mode; // 模式：1-制热，0-制冷
    private int windSpeed; // 风速：0-自动，1-高速，2-中速，3-低速
    private int settingTemperature; // 设定温度，设定范围10~30
    private int compensateTemperature; // 补偿温度，设置范围-9*C~9*C，默认（-2*C）
    private int indoorTemperature; // 室内温度，内置温度采集器
    private long scheduleTime; // 定时时间，正数表示开定时，负数表示关定时
    private boolean scheduleOn; // 定时开/关
    private int scheduleHour; // 定时时间段 // 初始定义为0
    private long time; // 设定掉线时间
    private int state; // 空调状态：1-开，0-关，2-掉线

    @Override
    public String toString() {
        return "AirConditioner{" +
                "addr=" + addr +
                ", addrnum='" + addrnum + '\'' +
                ", switch0=" + switch0 +
                ", mode=" + mode +
                ", windSpeed=" + windSpeed +
                ", settingTemperature=" + settingTemperature +
                ", compensateTemperature=" + compensateTemperature +
                ", indoorTemperature=" + indoorTemperature +
                ", scheduleTime=" + scheduleTime +
                ", scheduleOn=" + scheduleOn +
                ", scheduleHour=" + scheduleHour +
                ", time=" + time +
                ", state=" + state +
                '}';
    }

    public int getSwitch0() {
        return switch0;
    }

    public void setSwitch0(int switch0) {
        this.switch0 = switch0;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getSettingTemperature() {
        return settingTemperature;
    }

    public void setSettingTemperature(int settingTemperature) {
        this.settingTemperature = settingTemperature;
    }

    public int getCompensateTemperature() {
        return compensateTemperature;
    }

    public void setCompensateTemperature(int compensateTemperature) {
        this.compensateTemperature = compensateTemperature;
    }

    public int getIndoorTemperature() {
        return indoorTemperature;
    }

    public void setIndoorTemperature(int indoorTemperature) {
        this.indoorTemperature = indoorTemperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public boolean isScheduleOn() {
        return scheduleOn;
    }

    public void setScheduleOn(boolean scheduleOn) {
        this.scheduleOn = scheduleOn;
    }

    // 以分钟显示
    public int getScheduleHour() {
        if (scheduleOn) {
            return (int) ((this.scheduleTime - new Date().getTime()) / 1000 / 60); // 定时开
        } else {
            return -(int) ((this.scheduleTime - new Date().getTime()) / 1000 / 60); // 定时关
        }
//        return scheduleHour;
    }

    // 以小时接收
    public void setScheduleHour(int scheduleHour) {
        if (scheduleHour >= 0) {
            this.scheduleOn = true;
        } else if (scheduleHour < 0) {
            this.scheduleOn = false;
        }
        this.scheduleTime = new Date().getTime() + scheduleHour * 60 * 60 * 1000; // ms
        this.scheduleHour = scheduleHour;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
