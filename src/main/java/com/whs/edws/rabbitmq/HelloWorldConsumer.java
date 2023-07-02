package com.whs.edws.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloWorldConsumer {

    @RabbitListener(queuesToDeclare = {@Queue(name = "simpleQueue")})
    public void consumer(String msg){
        log.info("simple模式接收到的消息：" + msg);
    }
}
