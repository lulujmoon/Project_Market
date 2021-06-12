package com.mm.market.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonParser;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SocketHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 답아둘 맵

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 메시지 발송 (메시지를 수신하면 실행됨)
		String msg = message.getPayload();
		//System.out.println("msg : "+msg);
		JSONObject obj = jsonToObjectParser(msg);
		//System.out.println("obj : "+obj);
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			
			try {
				wss.sendMessage(new TextMessage(obj.toJSONString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		JSONObject obj = new JSONObject();
		//생성된 세션을 저장ㅎ사면 발신메시지 타입은 getId라고 명시 후 생성된 세션ID값을 클라이언트단으로 발송
		//클라이언트단에서는 type값을 통해 메시지와 초기 설정값을 구분
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}

	private static JSONObject jsonToObjectParser(String jsonStr){
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		try {
			obj = (JSONObject)parser.parse(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}


}
