package RabbitMq;

import com.rabbitmq.client.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;
@Configuration()
public class NewTask {
    private static final String QUENUE_NAME = "gg";

    public static void main(String[] args) {
        test2();
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
            channel.basicPublish(QUENUE_NAME, "", null, message.getBytes());
            System.out.println("[x] send message:" + message);

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
    public void test1() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUENUE_NAME, true, false, false, null);
            for (int i = 0; i < 10; ++i) {
                String dots = "";
                for (int j = 0; j < i; ++j) {
                    dots += ".";
                }
                String message = "hello world" + dots + dots.length();
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
