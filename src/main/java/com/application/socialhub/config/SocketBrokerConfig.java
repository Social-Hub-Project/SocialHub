//package com.application.socialhub.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.*;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class SocketBrokerConfig implements  WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/app/user/queue/specific-user");
//        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
//        config.setUserDestinationPrefix("/app/user");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/app/chatRoom").setAllowedOrigins("*").withSockJS();
//        registry.addEndpoint("/app/chatRoom").setAllowedOrigins("*");
//    }
//}