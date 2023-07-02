package com.whs.edws.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "boot-topic-exchange", type = "topic"),
            key = {"*.info.*"})
    })
    public void consumer(String msg){
        log.info("topic接收者1接受到的消息为："+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(name = "boot-topic-exchange", type = "topic"),
                    key = {"user.*.*"})
    })
    public void consumer2(String msg){
        log.info("topic接收者2接受到的消息为："+msg);
    }
}
