package com.dhlk.bas.controller;

import com.dhlk.bas.conf.ScheduleConfig;
import com.dhlk.bas.model.LightStateModel;
import com.dhlk.bas.util.FileUtil;
import com.dhlk.bas.util.LightUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@EnableScheduling
public class LightController {

    /**
     * 照明控制总开关
     *
     * @return
     */
    @GetMapping(value = "/light/switchMode/{mode}")
    public int switchMode(@PathVariable int mode) {
        int result = 1;
        try {
            LightUtil.lightSwitchMode = mode;
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    /**
     * 返回到单灯页面
     *
     * @param lightId
     * @return
     */
    @RequestMapping(value = "/light/{lightId}")
    public ModelAndView qrcodeView(@PathVariable String lightId) {

        ModelAndView mv = new ModelAndView("/qrcode/qrcode");
        mv.addObject("lightId", lightId);

        return mv;
    }

    /**
     * 返回到单灯页面
     *
     * @param lightId
     * @return
     */
    @RequestMapping(value = "/erweima/{lightId}")
    public ModelAndView lightView(@PathVariable String lightId) {

        ModelAndView mv = null;
        try {
            mv = new ModelAndView("/erweima/one");
            mv.addObject("lightId", lightId);
            if (lightId.contains("-") || lightId.contains("|")) {
                String[] lights = lightId.contains("-") ? lightId.split("-") : lightId.split("\\|");
                int lightState = 0;
                int lightStateCount = 0;
                int i;
                for (i = 0; i < lights.length; i++) {
                    String lightStateI = (String) FileUtil.lightStateMap.get("state_" + lights[i]);
                    if (lightStateI != null) {
                        lightState = Integer.parseInt(lightStateI);
                    }
                    if (lightState == 1) {
                        ++lightStateCount;
                    }
                }
                if (lightStateCount < i) {
                    mv.addObject("lightState", 0);
                } else {
                    mv.addObject("lightState", 1);
                }
            } else {
                int lightState = (int) FileUtil.lightStateMap.get(lightId);
                mv.addObject("lightState", lightState);
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 获取所有灯的状态
     *
     * @return
     */
    @GetMapping(value = "/light/state")
    public Map<String, Object> lightState() {

        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, Object> lightMap = FileUtil.lightMap;
            Iterator<String> iterator = lightMap.keySet().iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                LightStateModel lightModel = (LightStateModel) lightMap.get(next);
//                System.out.println(lightModel);
                int state = lightModel.getRelayState();
                long time = lightModel.getTime();

                map.put(next, state);

                // 判断是否过期，5分钟
                if ((new Date().getTime() - time) > 5 * 60 * 1000) { // 5 分钟
                    map.put(next, 2);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }

    /**
     * 调光接口
     *
     * @param shortaddrs
     * @param brightness
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/light/brightness/{shortaddrs}/{brightness}")
    public int lightBrightness(@PathVariable String shortaddrs, @PathVariable int brightness) throws UnsupportedEncodingException, InterruptedException {

        int flag = 1;

        try {
            LightUtil.lightBirghtness(shortaddrs, brightness);
        } catch (Exception e) {
            e.printStackTrace();
            flag = 0;
        }

        return flag;

    }

    /**
     * 开灯接口
     *
     * @param shortaddrs
     * @param state
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/light/switch/{shortaddrs}/{state}")
    public int lightSwitch(@PathVariable String shortaddrs, @PathVariable int state) throws UnsupportedEncodingException, InterruptedException {

        int flag = 1;

        try {
            LightUtil.lightSwitch(shortaddrs, state);
        } catch (Exception e) {
            e.printStackTrace();
            flag = 0;
        }

        return flag;
    }

    /**
     * 人员感应接口
     *
     * @param shortaddrs
     * @param state
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/light/induction/{shortaddrs}/{state}")
    public int lightInduction(@PathVariable String shortaddrs, @PathVariable int state) throws UnsupportedEncodingException, InterruptedException {

        int flag = 1;

        try {
            LightUtil.lightInduction(shortaddrs, state);
        } catch (Exception e) {
            e.printStackTrace();
            flag = 0;
        }

        return flag;
    }

    /**
     * 定时开灯
     */
    @Scheduled(cron = "0,5 45 8 ? * *")
    @Scheduled(cron = "0,5 55 13 ? * *")
    public void lightAutoOpen() throws UnsupportedEncodingException, InterruptedException {

        // 如果是非工作日
        if (ScheduleConfig.workDay == 0) {
            return;
        }

        System.out.println("-------------------------Schedule-------------------------");
        String addrList =
                "350|646|647|648|" + // 财务室
                        "644|645|" + // 技术办公室
                        "343|611|612|613|614|615|" + // 总经理办公室
                        "340|341|342|600|601|602|603|604|605|606|607|608|609|610|" + // 实验室
//                        "348|349|637|638|639|640|641|642|643|" + // 会议室
                        "347|633|634|635|636|" + // 前台
                        "354|665|666|667|669|670|671|672|" + // 餐厅
                        "344|345|346|351|352|353|355|616|617|618|619|620|621|622|623|624|625|626|627|628|629|630|631|632|" + // 大厅
                        "659|660|661|662|668|655|656|657|658|663|664|654|653|652|651|650|649"; // 大厅

        lightSwitch(addrList, 1);

    }

    /**
     * 定时关灯
     *
     * @throws UnsupportedEncodingException
     * @throws InterruptedException
     */
//    @Scheduled(cron = "0,5 0 18 ? * MON-FRI")
    public void lightAutoClose() throws UnsupportedEncodingException, InterruptedException {

        System.out.println("-------------------------Schedule-------------------------");
        String addrList = "354|665|666|667|669|670|671|672"; // 餐厅
        lightSwitch(addrList, 0);

    }


}
