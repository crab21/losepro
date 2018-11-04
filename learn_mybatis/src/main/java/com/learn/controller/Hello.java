package com.learn.controller;

import com.learn.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by k on 2018/6/7.
 */
@RestController
public class Hello {
    @Autowired
    UserServices userServices;

    @Autowired
    @Qualifier("titanMasterDS")
    DataSource sqlSessionTemplate;

    @RequestMapping("/hello")
    public String name() {
        String i = userServices.masterUser();
        System.out.println(i + "------------" + "来自master");
        return "ok";

    }

    @GetMapping("/hi")
    public String names() {
        int i = userServices.dsUser();
        System.out.println(i + "----------" + "来在ds2");
        return "ok";
    }

    @GetMapping("/judge")
    public void judge() {
        Connection connection = null;
        try {
            connection = sqlSessionTemplate.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, "usersss", new String[]{"TABLE"});

            System.out.println(tables.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
