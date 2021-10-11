package com.aitongyi.rabbit.publish;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author HouYongJu
 * @create 2021-09-26 20:31
 */
public class ReceiveLogs2 {
    private static final String EXCHANGE_NAME = "logs";
    private final static String IP_ADDRESS = "120.79.161.30";
    private final static int PORT = 5672;
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //		设置RabbitMQ地址、端口号、用户、密码
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("hEV8.b7i3MpiXR@");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
