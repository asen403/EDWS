package com.whs.edws.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldConsumerTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void simplePublish() {
        rabbitTemplate.convertAndSend("simpleQueue", "simple消息");
    }

    @Test
    void workPublish() {
        rabbitTemplate.convertAndSend("workQueue", "work消息".getBytes());
    }

    @Test
    void pubsubPublish() {
        rabbitTemplate.convertAndSend("boot-pubsub-exchange", "", "pubsub消息");
    }

    @Test
    void routePublish() {
        rabbitTemplate.convertAndSend("boot-route-exchange", "error", "这是一条error消息");
        rabbitTemplate.convertAndSend("boot-route-exchange", "info", "这是一条info消息");
        rabbitTemplate.convertAndSend("boot-route-exchange", "debug", "这是一条debug消息");
    }

    @Test
    void topicPublish() {
        rabbitTemplate.convertAndSend("boot-topic-exchange", "user.error.login", "这是一条error消息");
        rabbitTemplate.convertAndSend("boot-topic-exchange", "user.info.login", "这是一条info消息");
        rabbitTemplate.convertAndSend("boot-topic-exchange", "user.debug.login", "这是一条debug消息");
    }
}

