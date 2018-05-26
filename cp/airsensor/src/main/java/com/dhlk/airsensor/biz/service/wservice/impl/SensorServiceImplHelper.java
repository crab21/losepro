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
        senInfo.setSensor_addr(sensorInputBean.getAddr());

        dealTem(senInfo, sensorInputBean.getTem());
        list.add(senInfo);
        return list;
    }

    private static void dealTem(SenInfo senInfo, String tem) {
        String subResult = tem;
        if (tem.contains(splitDateDecli)) {
            splitData = tem.substring(tem.lastIndexOf(splitDateDecli) + 1, tem.length());
            subResult = tem.substring(0, tem.lastIndexOf(splitDateDecli));
        }

        String[] split = subResult.split(splitData);
        senInfo.setSensor_tem(split[0]);
        senInfo.setAir_tem(split[1]);

    }

    private static void dealDate(SenInfo senInfo, String date) {
        String subResult = date;
        if (date.contains(splitDateDecli)) {
            splitData = date.substring(date.lastIndexOf(splitDateDecli) + 1, date.length());
            subResult = date.substring(0, date.lastIndexOf(splitDateDecli));
        }
        String[] split = subResult.split(splitData);
        senInfo.setStart_date(split[0]);
        senInfo.setEnd_date(split[1]);
        splitData = "-";
    }
}
