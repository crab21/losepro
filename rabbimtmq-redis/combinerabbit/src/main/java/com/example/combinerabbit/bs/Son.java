package com.example.combinerabbit.bs;

/**
 * Created by k on 2018/6/19.
 */
public class Son extends Father {

    @Override
    public void add() {
        super.add();
        System.out.println("son");
    }

    @Override
    public void addall() {
        super.addall();
        System.out.println("addall son...");
    }


    public static void main(String[] args) {
        Son son = new Son();
        son.addall();
    }
}
