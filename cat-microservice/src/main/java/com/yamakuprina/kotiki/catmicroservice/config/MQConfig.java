package com.yamakuprina.kotiki.catmicroservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String CATS_QUEUE_ALL ="cat_all";
    public static final String CATS_QUEUE_ID ="cat_id";
    public static final String CATS_QUEUE_SAVE ="cat_save";
    public static final String CATS_QUEUE_DELETE ="cat_delete";
    public static final String CATS_QUEUE_COLOR ="cat_color";
    public static final String CATS_QUEUE_FRIENDS ="cat_friends";
    public static final String CATS_QUEUE_ADD_FRIEND ="cat_add_friend";
    public static final String CATS_QUEUE_DELETE_FRIEND ="cat_delete_friend";

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
