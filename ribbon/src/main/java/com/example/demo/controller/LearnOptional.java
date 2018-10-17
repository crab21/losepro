package com.example.demo.controller;

import java.util.Optional;

/**
 * Created by k on 2018/10/1.
 */
public class LearnOptional {
    public static void main(String[] args) {
        Integer result = null;
        result = 1;
        Optional<Integer> result1 = Optional.ofNullable(result);
        boolean present = result1.isPresent();
        if (present) {
            System.out.println("----------");
        } else {
            System.out.println("...................");
        }

    }


}
