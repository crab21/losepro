package com.dhlk.bas.util;

import com.dhlk.bas.conf.ScheduleConfig;
import com.dhlk.bas.model.AirCommand;
import com.dhlk.bas.model.AirConditioner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by South on 2017-03-07.
 */
public class AirConditionerUtil {

    public static String groupSend = "55AA0AFF000000";
    public static String airConditionerWord = "9307";

    // 设置总的开关
    public static int airconditionerSwitchMode = 1;

    /**
     * checkSum
     * 空调命令校验字
     *
     * @param hex
     * @return
     */
    public static String checkSum(String hex) {

        // 分析hex是否合法
        if (hex != null) {
            hex = hex.trim().replace(" ", "");
        }

        // 判断长度是否合适
        if (hex == null || hex.length() / 2 * 2 != hex.length() || hex.length() != 14) {
            return null;
        }

        BigInteger checkSum = new BigInteger("00", 16);

        for (int i = 0; i < 7; i++) {
            String sub = hex.substring(i * 2, i * 2 + 2);
            checkSum = checkSum.add(new BigInteger(sub, 16));
        }

        // and or
        checkSum = checkSum.and(new BigInteger("FF", 16)).xor(new BigInteger("A5", 16));
//        System.out.println(checkSum.toString(16));

        return checkSum.toString(16).toUpperCase();
    }

    /**
     * 段号地址号转换
     * 短地址之间通过"-"或"|"连接，如果是单个也不影响
     */

    public static List<String> getAirConditionerAddrNumWithShortAddr(String shortaddrs) {

        String[] addrArray;

        if (shortaddrs.contains("|")) {
            addrArray = shortaddrs.split("\\|");
        } else if (shortaddrs.contains("-")) {
            addrArray = shortaddrs.split("-");
        } else {
            addrArray = new String[]{shortaddrs};
        }

        List<String> addrnumList = new ArrayList<>();

        for (String addr : addrArray) {
            String addrnum = (String) FileUtil.airConditionerAddrMap.get(addr);
            addrnumList.add(addrnum);
        }

        return addrnumList;
    }

    /**
     * 获取第一个空调命令字
     *
     * @param shortaddrs
     * @return
     */
    public static AirCommand getFirstAirCommand(String shortaddrs) {

        String addr;

        if (shortaddrs.contains("|")) {
            addr = shortaddrs.split("\\|")[0];
        } else if (shortaddrs.contains("-")) {
            addr = shortaddrs.split("-")[0];
        } else {
            addr = shortaddrs;
        }

        AirCommand airCommand = (AirCommand) FileUtil.airConditionerCommandMap.get(addr);

        return airCommand;

    }

    public static void sendMessage(String[] addAddrArray, AirCommand airCommand) {

        if (airconditionerSwitchMode == 0) {
            return;
        }

        for (int i = 0; i < addAddrArray.length; i++) {

            String addradd = addAddrArray[i];
            String addraddLen = HexUtil.decimal2Hex(1 + addradd.length() / 2);
            String airConditionerCommand = airConditionerWord + airCommand.getMessage();
            String addraddCommandLen = HexUtil.decimal2Hex(1 + (addradd.length() + airConditionerCommand.length()) / 2);
            String checkCode = HexUtil.checkCode("F1" + addraddLen + addradd + airConditionerCommand);
            String hex = groupSend + HexUtil.getFrameNum() + addraddCommandLen + "F1" + addraddLen + addradd + airConditionerCommand + checkCode;

            // send
            System.out.println("hex:" + hex);
            SocketUtil.sendMessage(hex);
        }
    }

    /**
     * 空调参数设置
     *
     * @param shortaddrs
     * @param airConditioner2
     */
    public static void airConditionerSet(String shortaddrs, AirConditioner airConditioner2) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setSwitch0(airConditioner2.getSwitch0());
        airConditioner.setMode(airConditioner2.getMode());
        airConditioner.setWindSpeed(airConditioner2.getWindSpeed());
        airConditioner.setSettingTemperature(airConditioner2.getSettingTemperature());
        airConditioner.setCompensateTemperature(airConditioner2.getCompensateTemperature()); // 设置补偿温度为0

        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);
    }

    /**
     * 开关空调
     * 1：开灯；0：关灯
     *
     * @param shortaddrs
     * @param switch0
     * @return
     */
    public static void switch0(String shortaddrs, int switch0) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setSwitch0(switch0);
        int baseTemperature = ScheduleConfig.baseTemperature; // 获取基准温度
        if (month > 10 || month <= 4) { // 冬天
            airConditioner.setMode(1); // 制热
            airConditioner.setSettingTemperature(baseTemperature); // 设定基准温度
            airConditioner.setCompensateTemperature(2); // 设置补偿温度
        } else {
            airConditioner.setMode(0); // 制冷
            airConditioner.setSettingTemperature(baseTemperature); // 设定基准温度
            airConditioner.setCompensateTemperature(-2); // 设置补偿温度
        }
        airConditioner.setWindSpeed(0); // 自动
        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);

    }

    /**
     * 空调模式
     * 1：制热；0：制冷
     *
     * @param shortaddrs
     * @param mode
     * @return
     */
    public static void mode(String shortaddrs, int mode) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setMode(mode);
        if (month > 10 || month <= 4) { // 冬天
            airConditioner.setCompensateTemperature(2); // 设置补偿温度
        } else {
            airConditioner.setCompensateTemperature(-2); // 设置补偿温度
        }
        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);

    }

    /**
     * 设置空调风速
     * 0：自动；1：高速；2：中速；3：低速
     *
     * @param windSpeed
     * @return
     */
    public static void wind(String shortaddrs, int windSpeed) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setWindSpeed(windSpeed);
        if (month > 10 || month <= 4) { // 冬天
            airConditioner.setCompensateTemperature(2); // 设置补偿温度
        } else {
            airConditioner.setCompensateTemperature(-2); // 设置补偿温度
        }
        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);

    }

    /**
     * 设定空调温度
     * 设定范围：10~30（制冷和制热调节范围相同）
     *
     * @param shortaddrs
     * @param temperature
     * @return
     */
    public static void temperature(String shortaddrs, int temperature) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setSettingTemperature(temperature);
        if (month > 10 || month <= 4) { // 冬天
            airConditioner.setCompensateTemperature(2); // 设置补偿温度
        } else {
            airConditioner.setCompensateTemperature(-2); // 设置补偿温度
        }
        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);

    }

    /**
     * 设定空调补偿温度
     * 设定范围：-9*C~9*C，默认（-2*C）
     *
     * @param shortaddrs
     * @param compensateTemperature
     * @return
     */
    public static void compensateTemperature(String shortaddrs, int compensateTemperature) {

        // 获取段号地址号信息
        List<String> addrNumList = getAirConditionerAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = LightUtil.addAddrList(addrNumList);

        // 初始化空调命令
        AirCommand airCommand = getFirstAirCommand(shortaddrs);
        airCommand.setCommand("A1"); // 写命令
        AirConditioner airConditioner = airCommand.getAirConditioner();
        airConditioner.setCompensateTemperature(compensateTemperature);
        airCommand.setAirConditioner(airConditioner);

        sendMessage(addAddrArray, airCommand);

    }

    /**
     * 设置开关定时
     *
     * @param shortaddrs
     * @param scheduleOn
     * @param scheduleHour
     */
    public static void schedule(String shortaddrs, boolean scheduleOn, float scheduleHour) {


    }

}
