package com.yamakuprina.kotiki.ownermicroservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String OWNERS_QUEUE_ALL = "owners_all";
    public static final String OWNERS_QUEUE_ID = "owners_id";
    public static final String OWNERS_QUEUE_SAVE = "owners_save";
    public static final String OWNERS_QUEUE_DELETE = "owners_delete";

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
