package com.example.combinerabbit.bs;

/**
 * Created by k on 2018/6/19.
 */
public class Son2 extends Father {
    @Override
    public void add() {
        System.out.println("son2--add");
    }

    @Override
    public void addall() {
        super.addall();
    }
}
