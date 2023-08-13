package com.whs.edws.rabbitmq;

import com.whs.edws.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldConsumerTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private Logger logger= LoggerFactory.getLogger(RabbitTemplate.class);

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

    @Test
    void maxLengthTestPublish(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * @param correlationData 相关配置信息
             * @param ack             消息队列是否成功收到消息
             * @param cause           错误原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    logger.info("消息发送成功：" + correlationData.getId());
                } else {
                    logger.info("消息发送失败：" + correlationData.getId());
                    logger.info("错误原因：" + cause);
                }
            }
        });
        for (int i = 0; i < 11; i++) {
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(i));
            rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_QUEUE_ROUTING_KEY, String.valueOf(i), correlationData);
        }
    }
}

