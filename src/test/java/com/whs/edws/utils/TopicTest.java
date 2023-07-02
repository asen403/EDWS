package com.whs.edws.utils;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TopicTest {

    public static final String TOPIC_EXCHANGE = "topic-exchange";

    @Test
    void publish() throws Exception{
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取管道
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
        // 发布消息
        channel.basicPublish(TOPIC_EXCHANGE, "info.user.login", null, "info日志".getBytes());
        channel.basicPublish(TOPIC_EXCHANGE, "error.trade.trade", null, "error日志".getBytes());
        channel.basicPublish(TOPIC_EXCHANGE, "debug.user.login", null, "debug日志".getBytes());
        // 关闭资源
        channel.close();
        connection.close();
    }

    @Test
    void consumer1() throws Exception{
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取管道
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        // 声明交换机
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
        // 声明队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列
        channel.queueBind(queueName, TOPIC_EXCHANGE, "info.#");
        // 处理消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1接口到消息："+new String(body, StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queueName, false, consumer);

        // 关闭资源
        System.in.read();
        channel.close();
        connection.close();
    }

    @Test
    void consumer2() throws Exception{
        // 建立连接
        Connection connection = RabbitMqUtil.getConnection();
        // 获取管道
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        // 声明交换机
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
        // 声明队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列
        channel.queueBind(queueName, TOPIC_EXCHANGE, "*.user.*");
        // 处理消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2接口到消息："+new String(body, StandardCharsets.UTF_8));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queueName, false, consumer);

        // 关闭资源
        System.in.read();
        channel.close();
        connection.close();
    }
}
