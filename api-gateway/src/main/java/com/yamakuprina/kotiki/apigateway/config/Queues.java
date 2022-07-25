package com.yamakuprina.kotiki.apigateway.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Queues {
    public static final String EXCHANGE = "kotiki_exchange";
    public static final String ROUTING_KEY = "kotiki_routingKey";
    public static final String CATS_QUEUE_ALL = "cat_all";
    public static final String CATS_QUEUE_ID = "cat_id";
    public static final String CATS_QUEUE_SAVE = "cat_save";
    public static final String CATS_QUEUE_DELETE = "cat_delete";
    public static final String CATS_QUEUE_COLOR = "cat_color";
    public static final String CATS_QUEUE_FRIENDS = "cat_friends";
    public static final String CATS_QUEUE_ADD_FRIEND = "cat_add_friend";
    public static final String CATS_QUEUE_DELETE_FRIEND = "cat_delete_friend";
    public static final String OWNERS_QUEUE_ALL = "owners_all";
    public static final String OWNERS_QUEUE_ID = "owners_id";
    public static final String OWNERS_QUEUE_SAVE = "owners_save";
    public static final String OWNERS_QUEUE_DELETE = "owners_delete";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queueCatAll() {
        return new Queue(CATS_QUEUE_ALL);
    }

    @Bean
    public Binding bindingCatAll(Queue queueCatAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatAll).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_ALL);
    }

    @Bean
    public Queue queueCatId() {
        return new Queue(CATS_QUEUE_ID);
    }

    @Bean
    public Binding bindingCatId(Queue queueCatId, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatId).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_ID);
    }

    @Bean
    public Queue queueCatSave() {
        return new Queue(CATS_QUEUE_SAVE);
    }

    @Bean
    public Binding bindingCatSave(Queue queueCatSave, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatSave).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_SAVE);
    }

    @Bean
    public Queue queueCatDelete() {
        return new Queue(CATS_QUEUE_DELETE);
    }

    @Bean
    public Binding bindingCatDelete(Queue queueCatDelete, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatDelete).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_DELETE);
    }

    @Bean
    public Queue queueCatColor() {
        return new Queue(CATS_QUEUE_COLOR);
    }

    @Bean
    public Binding bindingCatColor(Queue queueCatColor, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatColor).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_COLOR);
    }

    @Bean
    public Queue queueCatFriends() {
        return new Queue(CATS_QUEUE_FRIENDS);
    }

    @Bean
    public Binding bindingCatFriends(Queue queueCatFriends, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatFriends).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_FRIENDS);
    }

    @Bean
    public Queue queueCatAddFriend() {
        return new Queue(CATS_QUEUE_ADD_FRIEND);
    }

    @Bean
    public Binding bindingCatAddFriend(Queue queueCatAddFriend, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatAddFriend).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_ADD_FRIEND);
    }

    @Bean
    public Queue queueCatDeleteFriend() {
        return new Queue(CATS_QUEUE_DELETE_FRIEND);
    }

    @Bean
    public Binding bindingCatDeleteFriend(Queue queueCatDeleteFriend, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCatDeleteFriend).to(topicExchange).with(ROUTING_KEY + CATS_QUEUE_DELETE_FRIEND);
    }

    @Bean
    public Queue queueOwnersAll() {
        return new Queue(OWNERS_QUEUE_ALL);
    }

    @Bean
    public Binding bindingOwnersAll(Queue queueOwnersAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueOwnersAll).to(topicExchange).with(ROUTING_KEY + OWNERS_QUEUE_ALL);
    }

    @Bean
    public Queue queueOwnersId() {
        return new Queue(OWNERS_QUEUE_ID);
    }

    @Bean
    public Binding bindingOwnersId(Queue queueOwnersId, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueOwnersId).to(topicExchange).with(ROUTING_KEY + OWNERS_QUEUE_ID);
    }

    @Bean
    public Queue queueOwnersSave() {
        return new Queue(OWNERS_QUEUE_SAVE);
    }

    @Bean
    public Binding bindingOwnersSave(Queue queueOwnersSave, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueOwnersSave).to(topicExchange).with(ROUTING_KEY + OWNERS_QUEUE_SAVE);
    }

    @Bean
    public Queue queueOwnersDelete() {
        return new Queue(OWNERS_QUEUE_DELETE);
    }

    @Bean
    public Binding bindingOwnersDelete(Queue queueOwnersDelete, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueOwnersDelete).to(topicExchange).with(ROUTING_KEY + OWNERS_QUEUE_DELETE);
    }
}
