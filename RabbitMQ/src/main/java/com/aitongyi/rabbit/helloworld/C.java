package com.aitongyi.rabbit.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author HouYongJu
 * @create 2021-09-26 16:51
 * 记录一下简单的命令
 * 被退回的消息
 * rabbitmqctl list_queues name messages_ready messages_unacknowledged
 * 交换器列表
 * 【rabbitmqctl list_exchanges】     指令列出服务器上所有可用的交换器
 * 绑定列表
 * 如果要查看绑定列表，可以执行【rabbitmqctl list_bindings】命令
 *
 */
public class C {
    private final static String QUEUE_NAME = "hello";
    private final static String IP_ADDRESS = "120.79.161.30";
    private final static int PORT = 5672;
    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
//		设置RabbitMQ地址
        factory.setHost(IP_ADDRESS);
        //设置端口号
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("hEV8.b7i3MpiXR@");
//		创建一个新的连接
        Connection connection = factory.newConnection();
//		创建一个信道
        Channel channel = connection.createChannel();
//		声明要关注的队列 -- 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("C [*] Waiting for messages. To exit press CTRL+C");
//		DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("C [x] Received '" + message + "'");
            }
        };
//		自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.close();
        connection.close();
    }

}
