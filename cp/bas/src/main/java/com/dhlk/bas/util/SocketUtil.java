package com.dhlk.bas.util;

import com.dhlk.bas.model.AirCommand;
import com.dhlk.bas.model.AirConditioner;
import com.dhlk.bas.model.LightStateModel;
import com.dhlk.bas.netty.DiscardServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.*;

/**
 * Created by fnan on 2017-11-09.
 */
public class SocketUtil {

    // 解析数据
    // 55bb0a0400004005430ee157cb0011002503b9000003ff79a5140000000000000000000000000000000000000000000000000000000000000000
    public static void dispatchMessage(String hex) {

        // 命令字
        String commond = hex.substring(20, 22);

        if (commond.equals("e1")) {

            // light
//            System.out.println("Light:" + hex);

            // addrNum
            String addrNum = hex.substring(6, 14);

            // power
            String voltageHex = hex.substring(22, 26);
            float voltage = HexUtil.hexToFloat(voltageHex) / 100.0f;

            // current
            String currentHex = hex.substring(26, 30);
            float current = HexUtil.hexToFloat(currentHex) / 100.0f;

            // power
            String powerHex = hex.substring(30, 34);
            float power = HexUtil.hexToFloat(powerHex);

            // powerFactor
            String powerFactorHex = hex.substring(34, 38);
            float powerFactor = HexUtil.hexToFloat(powerFactorHex) / 1000.0f;

            // relay 继电器
            String relayHex = hex.substring(38, 40);
            int relayState = 2;
            try {
                relayState = Integer.valueOf(relayHex) == 1 ? 0 : 1;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }

            // 打印采集日期
//            System.out.println(addrNum + " voltage: " + voltage + " current: " + current + " power: " + power + " powerFactor: " + powerFactor
//                    + " relayState: " + relayState);

            // 预先在FileUtil中进行初始化
            String addr = (String) FileUtil.lightAddrNumMap.get(addrNum);
            LightStateModel lightModel = (LightStateModel) FileUtil.lightMap.get(addr);
            lightModel.setVoltate(voltage);
            lightModel.setPower(power);
            lightModel.setCurrent(current);
            lightModel.setPowerFactor(powerFactor);
            lightModel.setRelayState(relayState);
            lightModel.setTime(new Date().getTime());
//            lightModel.setAddr(Integer.valueOf(addr));
//            lightModel.setAddrnum(addrNum);

            // light
//            String lightJSON = JSONObject.toJSONString(lightModel);
            try {
                FileUtil.lightMap.put(addr, lightModel);
                FileUtil.lightStateMap.put(addr, relayState);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(lightModel);
        } else if (commond.equals("93")) { // 设置空调信息

            // AirConditioner
//            System.out.println("AirConditioner:" + hex);

            String message = hex.substring(24, 40);
            String addrNum = hex.substring(6, 14);
            String addr = (String) FileUtil.airConditionerAddrNumMap.get(addrNum);
//            System.out.println(addr + ":" + hex);

            AirCommand airCommand = (AirCommand) FileUtil.airConditionerCommandMap.get(addr);
            airCommand.setMessage(message);
            airCommand.setAddr(Integer.valueOf(addr));
            airCommand.setAddrnum(addrNum);
            airCommand.setTime(new Date().getTime()); // 更新时间
            AirConditioner airConditioner = airCommand.getAirConditioner();
            FileUtil.airConditionerMap.put(addr, airConditioner); // 空调
            FileUtil.airConditionerStateMap.put(addr, airConditioner.getState()); // 空调状态

        }
    }

    // 发送消息
    public static void sendMessage(String hex) {

        Map<String, ChannelHandlerContext> ctxMap = DiscardServerHandler.ctxMap;
        Set<String> keySet = ctxMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            ChannelHandlerContext ctx = ctxMap.get(next);
            try {
                ByteBuf buffer = Unpooled.buffer(8);
                byte[] bytes = HexUtil.hexToByte(hex);
                buffer.writeBytes(bytes);
                ctx.writeAndFlush(buffer);
                ctx.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
