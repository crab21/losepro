package com.dhlk.airsensor.biz.service.airDeal;

import com.dhlk.airsensor.biz.model.HumidityTem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SensorDealAssist {
    /**
     * 分割处理数据
     *
     * @param map
     * @return
     */
    public static List<HumidityTem> splitThum(Map<String, List<String>> map) {
        List<HumidityTem> list = new ArrayList<>();
        System.out.println(map.size() + "++++++++");

        for (String str :
                map.keySet()) {
            List<String> lists = map.get(str);
            //没有数据  则返回为null
            if (lists.size() == 0) {
                return list;
            }
            //遍历里面的所有数据
            list.add(splitSensor(str, lists.get(0)));

        }
        return list;
    }

    /**
     * 截取指定的温度和湿度
     *
     * @param str
     * @param s
     * @return
     */
    public static HumidityTem splitSensor(String str, String s) {
        String humidity = s.substring(0, 3);
        humidity = humidity.substring(0, 2) + "." + humidity.substring(2, 3);
        String tem = s.substring(3, 6);
        tem = tem.substring(0, 2) + "." + tem.substring(2, 3);
        String date = s.substring(6, s.length());
        return new HumidityTem(str, humidity, tem, date);

    }
}
