package com.example.combinerabbit.config.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Product {

    public static void producer() throws MqttException, InterruptedException {
        String topic = "mqtt/wang";

        String content = "发送的内容...";

        int qos = 2;

        String broker = "tcp://47.106.231.162:61613";

        String userName = "admin";
        String password = "password";

        String clientId = "pubClient";

        MemoryPersistence memoryPersistence = new MemoryPersistence();

        MqttClient mqttClient = new MqttClient(broker, clientId, memoryPersistence);

        MqttConnectOptions conOption = new MqttConnectOptions();

        // 在重新启动和重新连接时候记住状态
        conOption.setCleanSession(false);

        conOption.setUserName(userName);
        conOption.setPassword(password.toCharArray());

        //遗嘱消息
        conOption.setWill("mqtt/lii", "endtime.....".getBytes(), 0, false);


        // 设置回调函数
        mqttClient.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                System.out.println("连接成功...");
            }

            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost");
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic:" + topic);
                System.out.println("Qos:" + message.getQos());
                System.out.println("message content:" + new String(message.getPayload()));

            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
                System.out.println(token.getClient().getClientId());

            }

        });
        //建立连接
        mqttClient.connect(conOption);
       /* MqttMessage message = new MqttMessage(content.getBytes());

        //设置消息服务质量
        message.setQos(1);*/


        int flag = 0;
        //发布消息
        while (true) {
            MqttMessage message = new MqttMessage((content + flag).getBytes());

            //设置消息服务质量
            message.setQos(2);
            mqttClient.publish(topic, message);
            Thread.sleep(100);
            if (++flag > 100) {
                break;
            }
        }
        //关闭通道
//        mqttClient.disconnect();
        mqttClient.close();
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        producer();

    }


}
