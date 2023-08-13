package com.whs.edws.config;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;

@Configuration
public class RabbitMQConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.business.exchange";
    public static final String DELAY_QUEUE_NAME = "delay.business.queue";
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.business.queue.routingKey";
    public static final String DEAD_LETTER_EXCHANGE_NAME = "dead.letter.exchange";
    public static final String DEAD_LETTER_QUEUE_NAME = "dead.letter.queue";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "dead.letter.queue.routingKey";

    // 声明延迟队列交换机
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    // 声明死信队列交换机
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE_NAME);
    }

    // 声明延时队列
    @Bean("delayQueue")
    public Queue delayQueue(){
        HashMap<String, Object> map = new HashMap<>();
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE_NAME);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING_KEY);
        // 设置该队列最大消息数
        map.put("x-max-length", 10);
        map.put("x-overflow", "reject-publish-dlx");
        return QueueBuilder.durable(DELAY_QUEUE_NAME).withArguments(map).build();
    }

    // 声明死信队列
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    // 声明延时队列的绑定关系
    @Bean
    public Binding delayBinding(@Qualifier("delayExchange") DirectExchange directExchange,
                                @Qualifier("delayQueue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(DELAY_QUEUE_ROUTING_KEY);
    }

    // 声明死信队列的绑定关系
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterExchange") DirectExchange directExchange,
                                     @Qualifier("deadLetterQueue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }
}
