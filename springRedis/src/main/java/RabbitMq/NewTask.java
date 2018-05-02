package RabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {
    private static final String QUENUE_NAME = "workqueue";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUENUE_NAME, false, false, false, null);
            for (int i = 0; i < 10; ++i) {
                String dots = "";
                for (int j = 0; j < i; ++j) {
                    dots += ".";
                }
                String message = "hello world" + dots + dots.length();
                channel.basicPublish("",QUENUE_NAME,null,message.getBytes());
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
