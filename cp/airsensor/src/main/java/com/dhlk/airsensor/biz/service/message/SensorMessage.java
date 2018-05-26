package com.dhlk.airsensor.biz.service.message;

import com.dhlk.airsensor.biz.model.SensorInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorMessage {

    @Autowired
    StringRedisTemplate redisTemplate;

    static Logger logger = LogManager.getLogger();

    /**
     * 发送指令
     *
     * @param list
     * @return
     */

    public Map getMessage(List<SensorInfo> list) {
        Map<String, List<String>> map = new HashMap<>();
        if (list.size() != 4) {
            try {
                throw new Exception("异常");
            } catch (Exception e) {
                logger.warn("yc-----------------start");
                logger.warn(e.getMessage());
                logger.warn("yc-----------------end");
            }
            return map;
        }
        ListOperations listOperations = redisTemplate.opsForList();
        System.out.println(list.size() + ">>>>>>>>>>>>>");
        for (SensorInfo addr :
                list) {
            List range = new ArrayList();
            //选择需要获取的元素
            String index = (String) listOperations.index(addr.getSensor_addr(), 0);
            range.add(index);
            map.put(addr.getSensor_addr(), range);
        }
        return map;
    }
}
