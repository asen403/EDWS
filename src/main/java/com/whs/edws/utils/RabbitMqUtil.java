package com.whs.edws.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtil {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("123.60.210.106");
        factory.setPort(5672);
        factory.setUsername("edws");
        factory.setPassword("403126");
        factory.setVirtualHost("/test");
        try {
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
