package com.dhlk.bas.util;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fnan on 2018-02-22.
 */
public class StringUtil {

    public static void main1(String[] args) {
        String str = "1-2-23-30";
        String[] splitArray = str.split("-");
        Integer[] intArray = new Integer[splitArray.length];

        for (int i=0;i<splitArray.length; i++) {
            intArray[i] = Integer.valueOf(splitArray[i]);
        }

        List<Integer> listInteger = Arrays.asList(intArray);
        boolean contains = listInteger.contains(2);
        System.out.println(contains);

        System.out.println(new Gson().toJson(intArray));
    }


}
