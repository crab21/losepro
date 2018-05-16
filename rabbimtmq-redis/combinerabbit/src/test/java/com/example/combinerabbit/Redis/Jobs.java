package com.example.combinerabbit.Redis;

import java.io.Serializable;

public class Jobs implements Serializable{
        private String name;
        private String id;
        private int time;

    public Jobs(String name, String id, int time) {
        this.name = name;
        this.id = id;
        this.time = time;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
}
