package com.example.demo.service;

import java.io.Serializable;

/**
 * Created by k on 2018/9/17.
 */

public class User  implements Serializable{
    private String name;
    private String password;
    private String userId;

    public User() {
    }

    public User(String name, String password, String userId) {
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
