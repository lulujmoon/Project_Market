package com.mm.market.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.mm.market.chat.SocketHandler;

@Configuration //해당 클래스가 Bean의 설정을 할 것임
@EnableWebSocketMessageBroker // WebSocket message handling 허용해줌
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { 

	@Override
	// MessageBroker는 송신자에게 수신자의 이전 메세지 프로토콜로 변환해주는 모듈 중 하나
	// 요청이 오면 그에 해당하는 통신 채널로 전송, 응답 또한 같은 경로로 가서 응답한다.
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/receive"); // 메세지 응답 prefix
		registry.setApplicationDestinationPrefixes("/send"); // 클라이언트에서 메세지 송신 시 붙여줄 prefix
	}
	
	@Override
	// 최초 소켓 연결 시 endpoint
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/teamhash-websocket").withSockJS(); // javascript에서 SockJS생성자를 통해 연결
	}
	
}
