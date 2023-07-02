package com.whs.edws.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class HelloWorldConsumer {

    @RabbitListener(queuesToDeclare = {@Queue(name = "simpleQueue")})
    public void consumer(String msg, Channel channel, Message message) throws IOException {
        log.info("simple模式接收到的消息：" + msg);

        // 手动发送确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
