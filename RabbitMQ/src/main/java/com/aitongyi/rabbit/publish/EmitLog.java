package com.aitongyi.rabbit.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author HouYongJu
 * @create 2021-09-26 20:31
 */
public class EmitLog {
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

//      分发消息
        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

}
