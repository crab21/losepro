package com.example.combinerabbit;

import com.example.combinerabbit.Controller.mail.MailSenders;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    @Autowired
    static MailSenders mailSenders;

    public static void main(String[] args) {
       /* String wang = "wpy";
        try {
            wang = new String(wang.getBytes(), "iso-8859-1");
            System.out.println(wang);
        } catch (Exception e) {

        }
        System.out.println(wang);
*/
        String s = longPrefix(new String[]{"fower", "low", "flight"});
        System.out.println(s + "------");

    }

    public static String longPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; ++i) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }


    public static int lengthOfLongestSubstring(String s) {
     /*   int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }*/

        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            System.out.println(s.charAt(j));
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static String getEncoding(String str) {
        String encode[] = new String[]{
                "UTF-8",
                "ISO-8859-1",
                "GB2312",
                "GBK",
                "GB18030",
                "Big5",
                "Unicode",
                "ASCII"
        };
        for (int i = 0; i < encode.length; i++) {
            try {
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
                    return encode[i];
                }
            } catch (Exception ex) {
            }
        }

        return "";
    }

    public void EncodeTest() {

/*
        List<Integer> number = getNumber(134);
        for (Integer in : number) {
            System.out.println(in);
        }
*/
        String encode = "ISO-8859-1";
        try {
            String wang = new String("\\u53d1\\u9001\\u5230\\u53d1\\u9001\\u5230".getBytes(), "ASCII");
//            System.out.println(wang);
            String[] split = wang.split("\\\\u");

            String flag = "";
            for (int i = 1; i < split.length; ++i) {
                System.out.println(split[i]);
                flag += (char) Integer.valueOf(split[i], 16).intValue();
            }
            System.out.println(flag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
