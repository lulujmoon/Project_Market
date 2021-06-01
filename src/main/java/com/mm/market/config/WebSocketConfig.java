//package com.mm.market.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker //webSocket 사용가능
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//	
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/ws").withSockJS();
//	}
//	
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		
//	}
//
//	
//	
//}
