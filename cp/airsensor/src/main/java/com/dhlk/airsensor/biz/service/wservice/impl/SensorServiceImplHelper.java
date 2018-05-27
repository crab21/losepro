package com.dhlk.airsensor.biz.service.wservice.impl;

import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import com.dhlk.airsensor.conf.sensor.SensorInit;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SensorServiceImplHelper {
    private static String splitDateDecli = "#";
    private static String splitData = "-";

    /**
     * 温度和日期deal
     *
     * @param sensorInputBean
     * @return
     */
    public static List<SenInfo> deal(SensorInputBean sensorInputBean) {
        List<SenInfo> list = new ArrayList<>();
        //todo

        //日期处理
        SenInfo senInfo = new SenInfo();
        boolean b = dealDate(senInfo, sensorInputBean.getDate());
        if (!b) {
            return list;
        }
        //list.add();
        senInfo.setSensor_addr(sensorInputBean.getAddr());

        boolean b1 = dealTem(senInfo, sensorInputBean.getTem());
        if (!b1) {
            return list;
        }
        list.add(senInfo);
        return list;
    }

    /**
     * 处理两个温度值
     *
     * @param senInfo
     * @param tem
     */
    private static boolean dealTem(SenInfo senInfo, String tem) {
        if (StringUtils.isEmpty(tem)) {
            return false;
        }
        String subResult = tem;
        if (tem.contains(splitDateDecli)) {
            splitData = tem.substring(tem.lastIndexOf(splitDateDecli) + 1, tem.length());
            subResult = tem.substring(0, tem.lastIndexOf(splitDateDecli));
        }

        String[] split = subResult.split(splitData);
        senInfo.setSensor_tem(split[0]);
        senInfo.setAir_tem(split[1]);
        splitData = "-";
        return true;
    }

    /**
     * 处理日期
     *
     * @param senInfo
     * @param date
     */
    private static boolean dealDate(SenInfo senInfo, String date) {
        String subResult = date;
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        if (date.contains(splitDateDecli)) {
            splitData = date.substring(date.lastIndexOf(splitDateDecli) + 1, date.length());
            subResult = date.substring(0, date.lastIndexOf(splitDateDecli));
        }
        String[] split = subResult.split(splitData);
        String s = split[0];
        String w = split[1];
        s = s.substring(0, 2) + "." + s.substring(2, s.length());
        s = w.substring(0, 2) + "." + w.substring(2, w.length());
        SensorInit.summerDate = s;
        SensorInit.winterDate = w;
        senInfo.setStart_date(s);
        senInfo.setEnd_date(w);
        splitData = "-";
        return true;
    }

    /**
     * 处理地址
     *
     * @param sensorInputBean
     * @return
     */
    public static SensorInfo dealAirAddr(SensorInputBean sensorInputBean) {
        String air_addrs = "";
        if (sensorInputBean.getAir_addr().contains("-")) {

            air_addrs = new Gson().toJson(sensorInputBean.getAir_addr().split("-"));
        }
        return new SensorInfo(sensorInputBean.getAddr(), air_addrs);
    }
}
