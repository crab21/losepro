package com.dhlk.airsensor.biz.service.message;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AirConSenor {
    //发送指令获取信息
    Map getMessage(List list);

}
