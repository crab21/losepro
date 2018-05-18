package com.dhlk.bas.util;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by fnan on 2017-12-14.
 */
public class LightUtil {

    // 设置灯的总开关状态
    public static int lightSwitchMode = 1;

    public static String groupSend = "55AA0AFF000000";
    public static String brightnessWord = "86";
    public static String switchWord = "85";
    public static String inductionWord = "84";

    /**
     * 段号地址号转换
     * 短地址之间通过"-"或"|"连接，如果是单个也不影响
     */

    public static List<String> getLightAddrNumWithShortAddr(String shortaddrs) {

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
            String addrnum = (String) FileUtil.lightAddrMap.get(addr);
            addrnumList.add(addrnum);
        }

        return addrnumList;
    }

    /**
     * 调光接口
     *
     * @param shortaddrs
     * @param brightness
     * @return
     */
    public static String lightBirghtness(String shortaddrs, int brightness) {

        List<String> addrList = getLightAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = addAddrList(addrList);

        for (int i = 0; i < addAddrArray.length; i++) {

            String addradd = addAddrArray[i];
            String brightness0 = HexUtil.decimal2Hex((int) (brightness / 100.0f * 255));
            String addraddLen = HexUtil.decimal2Hex(1 + addradd.length() / 2);
            String brightnessCommand = brightnessWord + brightness0;
            String addraddCommandLen = HexUtil.decimal2Hex(1 + (addradd.length() + brightnessWord.length() + brightness0.length()) / 2);
            String checkCode = HexUtil.checkCode("F1" + addraddLen + addradd + brightnessCommand);
            String hex = groupSend + HexUtil.getFrameNum() + addraddCommandLen + "F1" + addraddLen + addradd + brightnessWord + brightness0 + checkCode;

            sendMessage(hex);
        }

        return "";
    }

    /**
     * 人员感应
     *
     * @param shortaddrs
     * @param state
     * @return
     */
    public static String lightInduction(String shortaddrs, int state) {

        List<String> addrList = getLightAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = addAddrList(addrList);

        for (int i = 0; i < addAddrArray.length; i++) {

            String addradd = addAddrArray[i];
            String state0 = state == 1 ? "00" : "01";
            String addraddLen = HexUtil.decimal2Hex(1 + addradd.length() / 2);
            String inductionCommand = inductionWord + state0;
            String addraddCommandLen = HexUtil.decimal2Hex(1 + (addradd.length() + inductionWord.length() + state0.length()) / 2);
            String checkCode = HexUtil.checkCode("F1" + addraddLen + addradd + inductionCommand);
            String hex = groupSend + HexUtil.getFrameNum() + addraddCommandLen + "F1" + addraddLen + addradd + inductionWord + state0 + checkCode;

            sendMessage(hex);
        }

        return "";
    }

    /**
     * 开关灯
     *
     * @param shortaddrs
     * @param state
     * @return
     */
    public static String lightSwitch(String shortaddrs, int state) {

        List<String> addrList = getLightAddrNumWithShortAddr(shortaddrs);
        String[] addAddrArray = addAddrList(addrList);

        for (int i = 0; i < addAddrArray.length; i++) {

            String addradd = addAddrArray[i];
            String switch0 = state == 1 ? "00" : "01";
            String addraddLen = HexUtil.decimal2Hex(1 + addradd.length() / 2);
            String switchCommand = switchWord + switch0;
            String addraddCommandLen = HexUtil.decimal2Hex(1 + (addradd.length() + switchWord.length() + switch0.length()) / 2);
            String checkCode = HexUtil.checkCode("F1" + addraddLen + addradd + switchCommand);
            String hex = groupSend + HexUtil.getFrameNum() + addraddCommandLen + "F1" + addraddLen + addradd + switchWord + switch0 + checkCode;

            sendMessage(hex);

        }

        return "";
    }

    // 发送消息
    public static void sendMessage(String hex) {

        // 判断开关状态
        if (lightSwitchMode == 0) {
            return;
        }

        // send
        System.out.println("hex:" + hex);
        SocketUtil.sendMessage(hex);
    }


    /**
     * 群发命令地址相加
     * 如果段号超过5个，则使用 "-" 进行分割
     * 不同段号，按照16进制加法，计算计算
     */
    public static String[] addAddrList(List<String> addrList) {

        Map<String, String> addrMap = new HashMap<>();

        try {
            for (int i = 0; i < addrList.size(); i++) {
                String address = addrList.get(i);
                String addr = address.substring(0, 2);
                String num = address.substring(2, 8);
                if (addrMap.containsKey(addr)) {
                    String num2 = addrMap.get(addr);
                    num = new BigInteger(num2, 16).add(new BigInteger(num, 16)).toString(16);
                    while (num.length() < 6) {
                        num = "0" + num;
                    }
                    addrMap.put(addr, num);
                } else {
                    num = new BigInteger(num, 16).toString(16);
                    while (num.length() < 6) {
                        num = "0" + num;
                    }
                    addrMap.put(addr, num);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // AirCommand 拼接
        StringBuilder sb = new StringBuilder();
        Set<String> addrSet = addrMap.keySet();
        Iterator<String> iterator = addrSet.iterator();

        int i = 1;
        while (iterator.hasNext()) {
            String addr = iterator.next();
            String num = addrMap.get(addr);
            sb.append(addr + num);
            if (i % 3 == 0) {
                sb.append("-");
            }
            i++;
        }

        String[] sbArray;

        if (sb.toString().contains("-")) {
            sbArray = sb.toString().split("-");
        } else {
            sbArray = new String[]{sb.toString()};
        }

        return sbArray;
    }


    public static void main2(String[] args) {

        List<String> addrList = new ArrayList<>();

        addrList.add("80080000");
        addrList.add("80040000");
        addrList.add("80020000");
        addrList.add("80010000");
        addrList.add("40040000");
        addrList.add("40020000");
        addrList.add("40010000");
        addrList.add("20020000");
        addrList.add("20010000");
        addrList.add("10010000");

        addAddrList(addrList);
    }

}
