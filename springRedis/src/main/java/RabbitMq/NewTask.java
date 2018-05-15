package RabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeoutException;


public class NewTask {
    private static final String QUENUE_NAME = "gg";
    private static final String EXCHANGE_NAME = "ex_log_pro";
    private static final String[] SEVERITIES = {"info", "warning", "error"};

    public static void main(String[] args) {
        test1();
        /*new Thread(new Work()).start();
        Thread thread = new Thread(new Work2());
        thread.start();
        int i = 0;
        for(i = 0; i < 20;++i){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==16){
                thread.stop();
                break;
            }else{
                System.out.println("----"+i);
            }
        }*/

    }

    /**
     * routing方式
     */
    public static void test3() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            for (int i = 0; i < 100000; ++i) {
                String routKey = getRoutKey();
                String message = new Date().toString() + "--------" + i;
                channel.basicPublish(EXCHANGE_NAME, "error", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            }
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static String getRoutKey() {
        Random random = new Random();
        int ranval = random.nextInt(3);
        return SEVERITIES[ranval];
    }

    /**
     * 发布订阅模式
     */

    public static void test2() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(QUENUE_NAME, "fanout");
            String message = new Date().toString() + ":long something ----";
            channel.basicPublish(QUENUE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());


            Channel channel1 = connection.createChannel();
            channel1.exchangeDeclare("dd", "fanout");
            for (int i = 0; i < 100; ++i) {
                String message1 = new Date().toString() + "+++++++++++++++++++" + i;
                channel1.basicPublish("dd", "", MessageProperties.PERSISTENT_TEXT_PLAIN, message1.getBytes());
                System.out.println("[x] send message:" + message);
            }


            channel1.close();
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 工作队列模式
     */
    public static void test1() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUENUE_NAME, true, false, false, null);
            for (int i = 0; i < 20; ++i) {
                String dots = "";
                for (int j = 0; j < i; ++j) {
                    dots += ".";
                }
                String message = "hello world" + dots.length();
                channel.basicPublish("", QUENUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            }
            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
