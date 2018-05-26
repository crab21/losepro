package com.dhlk.bas.controller;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class te {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        System.out.println(gson.toJson(list));
        String[] name = {"1", "2", "3"};
        System.out.println(gson.toJson(name));
    }
}
