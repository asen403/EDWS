package com.whs.edws.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PubsubConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
            exchange = @Exchange(value = "boot-pubsub-exchange", type = "fanout"))
    })
    public void consumer(String msg){
        log.info("pubsub模式接收到的消息是：" + msg);
    }
}
