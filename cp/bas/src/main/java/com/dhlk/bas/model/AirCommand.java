package com.dhlk.bas.model;

import com.dhlk.bas.util.AirConditionerUtil;
import com.dhlk.bas.util.HexUtil;

import java.math.BigInteger;

/**
 * Created by fnan on 2017-11-29.
 * <p>
 * 空调控制器命令字段，总共8个字节
 * 封包协议：COMMAND + ID0 + ID1 + Data0 + Data1 + Data2 + Data3 + CheckSum
 * CheckSum = (COMMAND + ID0 + ID1 + Data0 + Data1 + Data2 + Data3) & 0xFF ^ 0xA5
 * 温控器默认地址：1033
 * </p>
 *
 * 空调协议：A1 33 10 20 02 1A 00 85
 * A1：写命令
 * 33：IDH
 * 10：IDL
 * ...
 * 85：checkSum
 */

public class AirCommand {

    // 空调状态类
    public AirConditioner airConditioner;

    private int addr; // 短地址
    private String addrnum; // 段号地址号

    // 字段列表
    private String message; // 应答包信息
    private String command; // 命令字：A0-读指令，50-返回指令；A1-写指令，51-返回指令
    private String iDL; // 控制器地址-低
    private String iDH; // 控制器地址-高
    private String data0; // 空调状态
    private String data1; // 补偿温度，默认为0
    private String data2; // 设定温度
    private String data3; // 室温数据
    private String checkSum; // 和校验
    private long time; // 时间戳

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        airConditioner.setAddr(addr);
        this.addr = addr;
    }

    public String getAddrnum() {
        return addrnum;
    }

    public void setAddrnum(String addrnum) {
        airConditioner.setAddrnum(addrnum);
        this.addrnum = addrnum;
    }

    // 获取命令信息
    public String getMessage() {
        String hexMsg = command + iDL + iDH + getData0() + getData1() + getData2() + getData3();
        hexMsg += AirConditionerUtil.checkSum(hexMsg);
        return hexMsg;
    }

    // 解析命令信息
    public void setMessage(String message) {
        this.command = message.substring(0, 2);
        this.iDL = message.substring(2, 4);
        this.iDH = message.substring(4, 6);
        setData0(message.substring(6, 8));
        setData1(message.substring(8, 10));
        setData2(message.substring(10, 12));
        setData3(message.substring(12, 14));
    }

    public String getData0() {
        int mode = airConditioner.getMode();
        int switch0 = airConditioner.getSwitch0();
        int windSpeed = airConditioner.getWindSpeed();
        String smode = String.valueOf(mode); // 0 1
        String sswitch0 = String.valueOf(switch0); // 0 1
        String swindSpeed = HexUtil.decimal2Binary(windSpeed); // 00 01 10 11
        String d0 = HexUtil.binary2Hex("00" + smode + sswitch0 + "00" + swindSpeed);
        return d0;
    }

    public void setData0(String data0) {
        String d0 = HexUtil.hex2Binary(data0);
        while (d0.length() < 8) {
            d0 = "0" + d0;
        }
        StringBuilder dsb = new StringBuilder(d0);
        dsb = dsb.reverse();
        String mode = dsb.substring(5, 6);
        String switch0 = dsb.substring(4, 5);
        String windSpeed = dsb.substring(0, 2);
        airConditioner.setMode(new BigInteger(mode, 2).intValue());
        airConditioner.setSwitch0(new BigInteger(switch0, 2).intValue());
        airConditioner.setState(new BigInteger(switch0, 2).intValue()); // 设置空调状态
        airConditioner.setWindSpeed(new BigInteger(windSpeed, 2).intValue());
    }

    public String getData1() {
        String d1 = HexUtil.decimal2Hex(airConditioner.getCompensateTemperature());
        return d1;
    }

    public void setData1(String data1) {
        airConditioner.setCompensateTemperature(new BigInteger(data1, 16).intValue());
    }

    public String getData2() {
        String d2 = HexUtil.decimal2Hex(airConditioner.getSettingTemperature());
        return d2;
    }

    public void setData2(String data2) {
        airConditioner.setSettingTemperature(new BigInteger(data2, 16).intValue());
    }

    public String getData3() {
        String d3 = HexUtil.decimal2Hex(airConditioner.getIndoorTemperature());
        return d3;
    }

    public void setData3(String data3) {
        airConditioner.setIndoorTemperature(new BigInteger(data3, 16).intValue());
    }

    public String getiDL() {
        return iDL;
    }

    public void setiDL(String iDL) {
        this.iDL = iDL;
    }

    public String getiDH() {
        return iDH;
    }

    public void setiDH(String iDH) {
        this.iDH = iDH;
    }

    public String getCheckSum() {
        String hexMsg = command + iDL + iDH + getData0() + getData1() + getData2() + getData3();
        return AirConditionerUtil.checkSum(hexMsg);
//        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.airConditioner.setTime(time);
        this.time = time;
    }
}
