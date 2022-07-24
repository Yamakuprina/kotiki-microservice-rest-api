package config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Queues {
    public static final String EXCHANGE ="kotiki_exchange";
    public static final String ROUTING_KEY = "kotiki_routingKey";

//    @Bean
//    public Queue queueCatId(){
//        return new Queue(QUEUE_FIRST);
//    }
}
