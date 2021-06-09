package com.mm.market.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //해당 클래스가 Bean의 설정을 할 것임
@EnableWebSocketMessageBroker //webSocket 서버 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //웹소켓 연결을 구성하기 위한 메서드 구현, 제공
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConfig.class);
	
	@Override //클라이언트가 웹소켓 서버에 연결하는데 사용할 웹소켓 엔드 포인트 등록
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//엔드 포인트 구성에 withSockJS() 사용
		registry.addEndpoint("/broadcast")
				.withSockJS()
				.setHeartbeatTime(60_000);
		
	}
	
	@Override	//한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는데 사용될 메시지 브로커를 구성
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// "/user"으로 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
		registry.enableSimpleBroker("/user");
		// "/app"으로 시작되는 메시지가 message-handling method으로 라우팅 되어야 한다는 것을 명시
		registry.setApplicationDestinationPrefixes("/app");
		
		
		//라우팅 : 망에서, 각 메시지에서 목적지까지 갈 수 있는 여러 경로 중 한 가지 경로를 설정해 주는 과정 
	}

	
	
}
