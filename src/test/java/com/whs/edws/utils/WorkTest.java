package com.whs.edws.utils;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WorkTest {

    @Test
    void publish () throws IOException, TimeoutException {

        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 发布消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", "HelloWork", null, ("消息序号为："+i).getBytes());
        }
        // 关闭资源
        channel.close();
        connection.close();

    }

    @Test
    void consumer1() throws IOException, TimeoutException {
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 绑定队列
        channel.queueDeclare("HelloWork", true, false, false, null);
        // 处理消息
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("消费者1接收到：" + new String(body, StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("HelloWork", false, consumer);
        // 关闭资源
        System.in.read();
        channel.close();
        connection.close();
    }

    @Test
    void consumer2() throws IOException, TimeoutException {
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 绑定队列
        channel.queueDeclare("HelloWork", true, false, false, null);
        // 处理消息
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("消费者2接收到：" + new String(body, StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("HelloWork", false, consumer);
        // 关闭资源
        System.in.read();
        channel.close();
        connection.close();
    }
}
