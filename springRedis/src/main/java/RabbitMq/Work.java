package RabbitMq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Work {
    private final  static String QUEUE_NAME="workqueue";

    public static void main(String[] args) {
        int hashCode = Work.class.hashCode();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

       /*     channel.basicConsume(QUEUE_NAME,false,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                }
            });*/

       QueueingConsumer consumer = new QueueingConsumer(channel);
       channel.basicConsume(QUEUE_NAME,true,consumer);
       while (true){
           QueueingConsumer.Delivery delivery = consumer.nextDelivery();
           String message = new String(delivery.getBody());
           System.out.println("[x] received"+message);
       }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
