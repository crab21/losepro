package com.example.combinerabbit.Redis;

import java.io.*;

public class SerializeUtil {
    public static byte[] serialize(Object object){
        ObjectOutputStream os = null;
        ByteArrayOutputStream baos = null;
        baos = new ByteArrayOutputStream();
        try {
            os = new ObjectOutputStream(baos);
            os.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
