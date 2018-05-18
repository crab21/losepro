package com.dhlk.bas.util;

import com.google.gson.Gson;

import java.util.Calendar;

/**
 * Created by fnan on 2018-01-02.
 */
public class TestUtil {

    // 测试日期类
    public static void main2(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(month);
        System.out.println(day);
    }

    // 测试阳历和阴历相互转换
    public static void main3(String[] args) {

//        Solar solar = new Solar();
//        solar.setSolarDay(2);
//        solar.setSolarMonth(1);
//        solar.setSolarYear(2018);
//        Lunar lunar = LunarSolarUtil.SolarToLunar(solar);
//        System.out.println(lunar.getLunarDay());
//        System.out.println(lunar.getLunarMonth());

        LunarSolarUtil.Lunar lunar = new LunarSolarUtil.Lunar();
        lunar.setLunarDay(20);
        lunar.setLunarMonth(1);
        lunar.setLunarYear(2018);

        LunarSolarUtil.Solar solar = LunarSolarUtil.LunarToSolar(lunar);
        System.out.println(solar.getSolarDay());
        System.out.println(solar.getSolarMonth());
        System.out.println(solar.getSolarYear());

    }

    // 测试Java常用类
    public static void main4(String[] args) {
        String str = "1-2-23-31";
        String[] splitArray = str.split("-");
        Integer[] intArray = new Integer[splitArray.length];

        for (int i=0;i<splitArray.length; i++) {
            intArray[i] = Integer.valueOf(splitArray[i]);
        }

        System.out.println(new Gson().toJson(intArray));
    }

}
