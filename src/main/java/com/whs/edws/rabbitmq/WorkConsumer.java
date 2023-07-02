package com.whs.edws.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class WorkConsumer {


    @RabbitListener(queuesToDeclare = {@Queue(name = "workQueue")})
    public void workConsumer(byte[] msg){
        log.info("work模式接收到的消息为：" + new String(msg, StandardCharsets.UTF_8));

    }
}
