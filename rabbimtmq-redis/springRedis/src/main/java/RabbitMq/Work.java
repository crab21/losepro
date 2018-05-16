package RabbitMq;

import com.rabbitmq.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;


public class Work implements Runnable{
    private final static String QUEUE_NAME = "gg";
    private static final String EXCHANGE_NAME = "ex_log_pro";
    private static final String[] SEVERITIES = {"info", "warning", "error"};

    public static void main(String[] args) {
        test2();


    }


    public static void test4() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Channel channel = factory.newConnection().createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String queueName = channel.queueDeclare().getQueue();
            String routKey = getRoutKey();
            channel.queueBind(queueName, EXCHANGE_NAME, "error");

            channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body);
                    System.out.println(message);
                    channel.basicAck(envelope.getDeliveryTag(), false);

                }

            });
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
     * 发布订阅
     */

    public static void test3() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(QUEUE_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName, QUEUE_NAME, "");
            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[x]receiverd message:" + message);
                }
            });

            Channel channel1 = connection.createChannel();
            channel1.exchangeDeclare("dd", "fanout");
            String queueName2 = channel1.queueDeclare().getQueue();
            channel.queueBind(queueName2, "dd", "");
            channel1.basicConsume(queueName2, false, new DefaultConsumer(channel1) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[y]received message:" + message);
                    channel1.basicAck(envelope.getDeliveryTag(), false);
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 工作队列
     */
    public static void test2() {
        int hashCode = Work.class.hashCode();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            //公平分发
            channel.basicQos(1);
            final Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[x] received:" + message);
                    System.out.println("--------");
                    try {
                        Thread.sleep(5000);
                        doWork(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("[x] done");
                        //手动发送一次应答反馈
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            //打开应答机制
            boolean autoAck = false;
            channel.basicConsume(QUEUE_NAME, autoAck, consumer);
            System.out.println("-------------");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                System.out.println("----------");
            }
        }
    }

    public void test1() {
        int hashCode = Work.class.hashCode();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

       /*     channel.basicConsume(QUEUE_NAME,false,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                }
            });*/

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("[x] received" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        test2();
    }
}
