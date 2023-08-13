package com.whs.edws.rabbitmq;

import com.rabbitmq.client.Channel;
import com.whs.edws.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
public class MaxLengthConsumer {

    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUE_NAME)
    public void receive(Message message, Channel channel) throws IOException {
        String s = new String(message.getBody());
        log.info("死信队列消费者接收到消息：" + s);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
