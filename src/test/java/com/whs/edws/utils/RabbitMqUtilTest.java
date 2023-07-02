package com.whs.edws.utils;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMqUtilTest {

    @Test
    void getConnection() {
        System.out.println(RabbitMqUtil.getConnection());
    }

    @Test
    void publish() throws IOException, TimeoutException {
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取管道
        Channel channel = connection.createChannel();
        // 发布消息
        channel.basicPublish("", "HelloWorld", null, "我恨你".getBytes());
        // 关闭资源
        channel.close();
        connection.close();
    }

    @Test
    void consumer() throws IOException, TimeoutException {
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取管道
        Channel channel = connection.createChannel();
        // 绑定队列
        channel.queueDeclare("HelloWorld", true, false, false, null);
        // 创建监听事件
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者接收到消息："+ new String(body, StandardCharsets.UTF_8));
            }
        };
        channel.basicConsume("HelloWorld", true, consumer);
        // 持续监听
        System.in.read();

        // 关闭资源
        channel.close();
        connection.close();
    }

}