package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by k on 2018/9/3.
 */
public class MongodbTemplateLearn {
    @Autowired
    MongoTemplate mongoTemplate;

    public String insert() {
        mongoTemplate.findOne()
        return "ok";
    }

}
