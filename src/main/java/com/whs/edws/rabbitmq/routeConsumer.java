package com.whs.edws.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class routeConsumer {


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "boot-route-exchange", type = "direct"),
            key = {"info", "debug"})
    })
    public void consumer(String msg){
        log.info("route模式1接收到的消息为：" + msg);

    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,
                    exchange = @Exchange(name = "boot-route-exchange", type = "direct"),
                    key = {"error"})
    })
    public void consumer2(String msg){
        log.info("route模式2接收到的消息为：" + msg);

    }
}
