package com.mm.market.chat;

public class KafkaConstants {
	// 생성한 토픽의 이름. 동일한 이름으로 토픽 생성 예정
	public static final String KAFKA_TOPIC = "kafka-chat";
	// consumer을 식별할 수 있는 그룹
	public static final String GROUP_ID = "lemon";
	// 카프카 클러스터에 연결하기 위한 호스트:포트 값
	public static final String KAFKA_BROKER = "localhost:9092";
}
