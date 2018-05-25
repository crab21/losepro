package com.dhlk.bas.util;

/**
 * Created by fnan on 2017-12-28.
 * 二氧化碳-温湿度-
 */

public class CarbonDioxideUtil {

    private static String sendMsg = "01030000000305CB";
    // 返回
    // 010306014F00E1023AA42F

    public static void init(String[] args) {

        try {

            Thread.sleep(2000);

            while (true) {
                Thread.sleep(1000);
//                SocketUtil.sendMessage(sendMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
