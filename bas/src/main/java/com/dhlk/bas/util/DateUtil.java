package com.dhlk.bas.util;

import com.google.gson.Gson;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by fnan on 2018-01-02.
 */
public class DateUtil {

    // 调休日期


    // 判断是否是节假日
    public static boolean isHoliday() {

        List<String> solarDateList = new ArrayList<>(); // 阳历日期
        List<String> lunarDateList = new ArrayList<>(); // 阴历日期
        List<String> specialDateList = new ArrayList<>(); // 调休日期，很烦

        // 日历对象
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String today = sdf.format(calendar.getTime());

        // 元旦（阳历）
        solarDateList.add("12-30");
        solarDateList.add("12-31");
        solarDateList.add("01-01");

        /**
         * 清明节
         * [Y*D+C]-L
         * Y=年数后2位，D=0.2422，L=闰年数，21世纪C=4.81，20世纪=5.59
         * 清明日期爲4月4日～6日之間。
         * 举例说明：2088年清明日期=[88×.0.2422+4.81]-[88/4]=26-22=4，4月4日是清明。
         * 20世纪-32世纪
         * 5.59, 4.82, 5.02, 5.26, 5.48, 4.70, 4.92, 5.135, 5.36, 4.60, 4.81, 5.04, 5.26
         */
        int year = calendar.get(Calendar.YEAR);
        year = year % 100; // 取年
        float C = 4.81f;
        float D = 0.2422f;
        float Y = year;
        float v = (Y * D + C) - (Y / 4);
        int vv = Math.round(v);
        solarDateList.add("04-0" + vv);
        solarDateList.add("04-0" + (vv + 1));
        solarDateList.add("04-0" + (vv + 2));

        // 劳动节
        solarDateList.add("04-29");
        solarDateList.add("04-30");
        solarDateList.add("05-01");

        // 国庆节
        solarDateList.add("10-01");
        solarDateList.add("10-02");
        solarDateList.add("10-03");
        solarDateList.add("10-04");
        solarDateList.add("10-05");
        solarDateList.add("10-06");
        solarDateList.add("10-07");

        /**
         * 阴历
         */

        // 春节
        lunarDateList.add("12-30");
        lunarDateList.add("01-01");
        lunarDateList.add("01-02");
        lunarDateList.add("01-03");
        lunarDateList.add("01-04");
        lunarDateList.add("01-05");
        lunarDateList.add("01-06");

        // 端午
        lunarDateList.add("05-03");
        lunarDateList.add("05-04");
        lunarDateList.add("05-05");

        // 中秋节
        lunarDateList.add("08-13");
        lunarDateList.add("08-14");
        lunarDateList.add("08-15");

        // 判断是否时节假日
        boolean flag = false;
        if (lunarDateList.contains(today) || solarDateList.contains(today)) {
            flag = true;
        }

        return flag;
    }

    public static void main2(String[] args) {

        System.out.println(isHoliday());

        LunarSolarUtil.Solar solar = new LunarSolarUtil.Solar();
        solar.solarYear = 2018;
        solar.solarMonth = 2;
        solar.solarDay = 20;

//        Lunar l= new Lunar();
//        l.isleap=false;
//        l.lunarDay=12;
//        l.lunarMonth=7;
//        l.lunarYear=2100;
        LunarSolarUtil.Lunar lunar = LunarSolarUtil.SolarToLunar(solar);
        System.out.println(new Gson().toJson(solar));
        System.out.println(new Gson().toJson(lunar));

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        System.out.println(sdf.format(calendar.getTime()));

    }

}
