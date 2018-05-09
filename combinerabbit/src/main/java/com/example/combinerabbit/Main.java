package com.example.combinerabbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> number = getNumber(134);
        for (Integer in : number) {
            System.out.println(in);
        }
    }


    public static List getNumber(int date) {
        int[] inte = new int[]{2, 4, 8, 16, 32, 64, 128};
        List<Integer> list = new ArrayList<>();
        int i = 0;

        i = Arrays.binarySearch(inte, date);
        if (i > 0) {
            list.add(inte[i]);
            return list;
        }
        i = Math.abs(i) - 1;
        if (i < inte.length - 1) {
            if (inte[i] == inte[i + 1]) {
                list.add(inte[i]);
                return list;
            }
        }
        list.add(inte[i - 1]);
        date = date - inte[i - 1];

        while (date != 0) {
            if (i - 2 < 0) {
                break;
            }
            if ((date - inte[i - 2]) >= 0) {
                date = date - inte[i - 2];
                list.add(inte[i - 2]);
            } else {
                --i;
                continue;
            }
            --i;
        }
        return list;
    }
}
