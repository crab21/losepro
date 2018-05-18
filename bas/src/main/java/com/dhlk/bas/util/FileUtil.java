package com.dhlk.bas.util;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.bas.model.AirCommand;
import com.dhlk.bas.model.AirConditioner;
import com.dhlk.bas.model.LightStateModel;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fnan on 2017-11-10.
 */
public class FileUtil {

    // light
    public static Map<String, Object> lightAddrMap = new HashMap<>();
    public static Map<String, Object> lightAddrNumMap = new HashMap<>();
    public static Map<String, Object> lightMap = new HashMap<>();
    public static Map<String, Object> lightStateMap = new HashMap<>();

    // airconditioner
    public static Map<String, Object> airConditionerAddrMap = new HashMap<>();
    public static Map<String, Object> airConditionerAddrNumMap = new HashMap<>();
    public static Map<String, Object> airConditionerMap = new HashMap<>();
    public static Map<String, Object> airConditionerStateMap = new HashMap<>();
    public static Map<String, Object> airConditionerCommandMap = new HashMap<>();


    public static void init(String[] args) {

        // light json file
        String jsonLight = readLightJsonFile();
//        System.out.println(jsonLight);

        List<Map<String, Object>> lightAddressList = JSONObject.parseObject(jsonLight, new TypeToken<List<Map<String, Object>>>() {
        }.getType());

        for (int i = 0; i < lightAddressList.size(); i++) {
            Map<String, Object> lightMap2 = lightAddressList.get(i);
            String addr = String.valueOf(lightMap2.get("addr"));
            String addrnum = String.valueOf(lightMap2.get("addrnum"));
            lightAddrMap.put(addr, addrnum);
            lightAddrNumMap.put(addrnum, addr);

            LightStateModel lightModel = new LightStateModel();
            lightModel.setAddr(Integer.valueOf(addr));
            lightModel.setAddrnum(addrnum);
            lightModel.setRelayState(2);
            lightModel.setTime(new Date().getTime());
            lightMap.put(addr, lightModel);
            lightStateMap.put(addr, 2);
        }

        // airconditioner json file
        String jsonAirConditioner = readAirConditionerJsonFile();
//        System.out.println(jsonAirConditioner);

        List<Map<String, Object>> airConditionerAddressList = JSONObject.parseObject(jsonAirConditioner, new TypeToken<List<Map<String, Object>>>() {
        }.getType());

        for (int i = 0; i < airConditionerAddressList.size(); i++) {
            Map<String, Object> airConditionerMap2 = airConditionerAddressList.get(i);
            String addr = String.valueOf(airConditionerMap2.get("addr"));
            String addrnum = (String) airConditionerMap2.get("addrnum");
            airConditionerAddrMap.put(addr, addrnum);
            airConditionerAddrNumMap.put(addrnum, addr);

            AirCommand airCommand = new AirCommand();
            airCommand.setiDH("10");
            airCommand.setiDL("33");
            AirConditioner airConditioner = new AirConditioner();
            airCommand.setAirConditioner(airConditioner);
            airCommand.setAddr(Integer.valueOf(addr));
            airCommand.setAddrnum(addrnum);
            airCommand.setCommand("50");
            airCommand.setTime(new Date().getTime());
//            airConditioner.setAddr(Integer.valueOf(addr));
//            airConditioner.setAddrnum(addrnum);
//            airConditioner.setTime(new Date().getTime());
            airConditioner.setState(2); // 掉线状态
            airConditioner.setScheduleTime(new Date().getTime()); // 定时时间为当前时间
            airConditionerMap.put(addr, airConditioner);
            airConditionerStateMap.put(addr, 2);
            airConditionerCommandMap.put(addr, airCommand);

        }

//        System.out.println(airConditionerAddrMap);
//        System.out.println(airConditionerAddrNumMap);
//        System.out.println(airConditionerMap);
//        System.out.println(airConditionerStateMap);
//        System.out.println(airConditionerCommandMap);

    }

    /**
     * readAirConditionerJsonFile
     * airconditioner shortaddr and addrnum
     *
     * @return
     */
    public static String readAirConditionerJsonFile() {
        return "[\n" +
                "  {\n" +
                "    \"addr\": 801,\n" +
                "    \"addrnum\": \"80800000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 802,\n" +
                "    \"addrnum\": \"80400000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 803,\n" +
                "    \"addrnum\": \"80200000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 804,\n" +
                "    \"addrnum\": \"80100000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 805,\n" +
                "    \"addrnum\": \"80080000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 806,\n" +
                "    \"addrnum\": \"80040000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 807,\n" +
                "    \"addrnum\": \"80020000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 808,\n" +
                "    \"addrnum\": \"80010000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 809,\n" +
                "    \"addrnum\": \"80008000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 810,\n" +
                "    \"addrnum\": \"80004000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 811,\n" +
                "    \"addrnum\": \"80002000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 812,\n" +
                "    \"addrnum\": \"80001000\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 813,\n" +
                "    \"addrnum\": \"80000800\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 814,\n" +
                "    \"addrnum\": \"80000400\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 815,\n" +
                "    \"addrnum\": \"80000200\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 816,\n" +
                "    \"addrnum\": \"80000100\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 817,\n" +
                "    \"addrnum\": \"80000080\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 818,\n" +
                "    \"addrnum\": \"80000040\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 819,\n" +
                "    \"addrnum\": \"80000020\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"addr\": 820,\n" +
                "    \"addrnum\": \"80000010\"\n" +
                "  }\n" +
                "]";
    }

    /**
     * readLightJsonFile
     * light shortaddr and addrnum
     *
     * @return
     */
    public static String readLightJsonFile() {

        return "[\n" +
                "  {\n" +
                "    \"addrnum\": \"01800000\",\n" +
                "    \"addr\": 340\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01080000\",\n" +
                "    \"addr\": 341\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000800\",\n" +
                "    \"addr\": 342\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02800000\",\n" +
                "    \"addr\": 343\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02008000\",\n" +
                "    \"addr\": 344\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000080\",\n" +
                "    \"addr\": 345\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03800000\",\n" +
                "    \"addr\": 346\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03008000\",\n" +
                "    \"addr\": 347\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03000080\",\n" +
                "    \"addr\": 348\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04800000\",\n" +
                "    \"addr\": 349\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04008000\",\n" +
                "    \"addr\": 350\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000080\",\n" +
                "    \"addr\": 351\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05800000\",\n" +
                "    \"addr\": 352\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05008000\",\n" +
                "    \"addr\": 353\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000080\",\n" +
                "    \"addr\": 354\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06800000\",\n" +
                "    \"addr\": 355\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01040000\",\n" +
                "    \"addr\": 600\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01020000\",\n" +
                "    \"addr\": 601\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01010000\",\n" +
                "    \"addr\": 602\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01008000\",\n" +
                "    \"addr\": 603\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01004000\",\n" +
                "    \"addr\": 604\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000400\",\n" +
                "    \"addr\": 605\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000200\",\n" +
                "    \"addr\": 606\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000080\",\n" +
                "    \"addr\": 607\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000040\",\n" +
                "    \"addr\": 608\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000020\",\n" +
                "    \"addr\": 609\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"01000010\",\n" +
                "    \"addr\": 610\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02400000\",\n" +
                "    \"addr\": 611\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02200000\",\n" +
                "    \"addr\": 612\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02100000\",\n" +
                "    \"addr\": 613\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02080000\",\n" +
                "    \"addr\": 614\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02040000\",\n" +
                "    \"addr\": 615\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02004000\",\n" +
                "    \"addr\": 616\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02002000\",\n" +
                "    \"addr\": 617\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02001000\",\n" +
                "    \"addr\": 618\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000800\",\n" +
                "    \"addr\": 619\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000400\",\n" +
                "    \"addr\": 620\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000200\",\n" +
                "    \"addr\": 621\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000040\",\n" +
                "    \"addr\": 622\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000020\",\n" +
                "    \"addr\": 623\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000010\",\n" +
                "    \"addr\": 624\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000008\",\n" +
                "    \"addr\": 625\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000004\",\n" +
                "    \"addr\": 626\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"02000002\",\n" +
                "    \"addr\": 627\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03400000\",\n" +
                "    \"addr\": 628\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03200000\",\n" +
                "    \"addr\": 629\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03100000\",\n" +
                "    \"addr\": 630\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03080000\",\n" +
                "    \"addr\": 631\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03040000\",\n" +
                "    \"addr\": 632\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03004000\",\n" +
                "    \"addr\": 633\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03002000\",\n" +
                "    \"addr\": 634\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03001000\",\n" +
                "    \"addr\": 635\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03000800\",\n" +
                "    \"addr\": 636\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03000040\",\n" +
                "    \"addr\": 637\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03000020\",\n" +
                "    \"addr\": 638\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"03000010\",\n" +
                "    \"addr\": 639\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04400000\",\n" +
                "    \"addr\": 640\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04200000\",\n" +
                "    \"addr\": 641\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04100000\",\n" +
                "    \"addr\": 642\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04080000\",\n" +
                "    \"addr\": 643\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04004000\",\n" +
                "    \"addr\": 644\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04002000\",\n" +
                "    \"addr\": 645\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04001000\",\n" +
                "    \"addr\": 646\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000800\",\n" +
                "    \"addr\": 647\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000400\",\n" +
                "    \"addr\": 648\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000040\",\n" +
                "    \"addr\": 649\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000020\",\n" +
                "    \"addr\": 650\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000010\",\n" +
                "    \"addr\": 651\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000008\",\n" +
                "    \"addr\": 652\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000004\",\n" +
                "    \"addr\": 653\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"04000002\",\n" +
                "    \"addr\": 654\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05400000\",\n" +
                "    \"addr\": 655\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05200000\",\n" +
                "    \"addr\": 656\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05100000\",\n" +
                "    \"addr\": 657\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05080000\",\n" +
                "    \"addr\": 658\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05004000\",\n" +
                "    \"addr\": 659\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05002000\",\n" +
                "    \"addr\": 660\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05001000\",\n" +
                "    \"addr\": 661\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000800\",\n" +
                "    \"addr\": 662\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000040\",\n" +
                "    \"addr\": 663\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000020\",\n" +
                "    \"addr\": 664\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000010\",\n" +
                "    \"addr\": 665\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000008\",\n" +
                "    \"addr\": 666\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"05000004\",\n" +
                "    \"addr\": 667\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06400000\",\n" +
                "    \"addr\": 668\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06200000\",\n" +
                "    \"addr\": 669\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06100000\",\n" +
                "    \"addr\": 670\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06080000\",\n" +
                "    \"addr\": 671\n" +
                "  },\n" +
                "  {\n" +
                "    \"addrnum\": \"06040000\",\n" +
                "    \"addr\": 672\n" +
                "  }\n" +
                "]";
    }

}
