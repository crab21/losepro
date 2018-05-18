package com.dhlk.bas.service;

/**
 * Created by Administrator on 2016/11/24.
 */
public interface LightControlService {

    // 开关灯
    Boolean lightStatus(int state, String address);

    // 设置灯亮度
    Boolean lightBrightness(float brightness, String address);


}
