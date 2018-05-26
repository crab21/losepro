package com.dhlk.airsensor.biz.service.wservice.impl;

import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.bean.SensorInputBean;

import java.util.ArrayList;
import java.util.List;

public class SensorServiceImplHelper {
    private static String splitDateDecli = "#";
    private static String splitData = "-";

    public static List<SenInfo> deal(SensorInputBean sensorInputBean) {
        List<SenInfo> list = new ArrayList<>();
        //todo

        //日期处理
        SenInfo senInfo = new SenInfo();
        dealDate(senInfo, sensorInputBean.getDate());
        //list.add();
        return null;
    }

    private static void dealDate(SenInfo senInfo, String date) {
        if (date.contains(splitDateDecli)) {
            splitData = date.substring(date.lastIndexOf(splitDateDecli), date.length() - 1);
        }
        String subResult = date.substring(0, date.lastIndexOf(splitDateDecli));
        String[] split = subResult.split(splitData);
        senInfo.setStart_date(split[0]);
        senInfo.setEnd_date(split[1]);
    }
}
