package com.mm.market.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SocketHandler extends TextWebSocketHandler {
	
	//HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 답아둘 맵
	List<HashMap<String, Object>> rls = new ArrayList<>();  //웹소켓 세션을 담아둘 리스트 -- roomListSessions
	
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	Map<String, WebSocketSession> userSessions = new HashMap<>();
	
	@Override	//현재 방번호를 가져오고 방정보+세션정보를 관리하는 rls리스트 컬랙션에서 데이터를 조회한 후  해당 Hashmap을 임시 맵에 파밍하여 roomNumber의 키 값을 제외한 모든 세션키값들을 웹소켓을 통해 메시지를 보냄(방구분)
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		System.out.println("handleTextMessage : " + session + " : " + message);
//		System.out.println(message.getPayload());
		// 메시지 발송 (메시지를 수신하면 실행됨)
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		
		String rN = (String)obj.get("roomNumber");
		HashMap<String, Object> temp = new HashMap<String, Object>();
		if(rls.size()>0) {
			for(int i=0;i<rls.size();i++) {
				String roomNumber = (String)rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
				if(roomNumber.equals(rN)) {	//같은값의 방이 존재하면
					temp = rls.get(i);	//해당 방번호의 세션리스트의 존재하는 모든 object값을 가져옴
					break;
				}
			}
			//해당 방의 세션들만 찾아 메시지를 발송
			for(String k : temp.keySet()) {
				if(k.equals("roomNumber")) { //다만 방번호일 경우 건너뛴다
					continue;
				}
				
				WebSocketSession wss = (WebSocketSession)temp.get(k);
				if(wss!=null) {
					try {
						wss.sendMessage(new TextMessage(obj.toJSONString()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 소켓 연결
		System.out.println("afterConnectionEstablishd : " + session);
		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		System.out.println(url);
		String roomNumber = url.split("/chating/")[1];
		int idx = rls.size(); //방의 사이즈 조사
		if(rls.size()>0) {
			for(int i=0;i<rls.size();i++) {
				String rN = (String) rls.get(i).get("roomNumber");
				if(rN.equals(roomNumber)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}
		
		//세션을 저장할때, 현재 접근한 방의 정보가 있는지 체크하고 존재하지 않으면 방의 번호를 입력 후 세션들을 담아줌
		if(flag) { //존재하는 방이라면 세션만 추가
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		} else { //최소 생성하는 방이라면 방번호와 세션을 추가
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}
		
		//세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송
		JSONObject obj = new JSONObject();
		//생성된 세션을 저장하면 발신메시지 타입은 getId라고 명시 후 생성된 세션ID값을 클라이언트단으로 발송
		//클라이언트단에서는 type값을 통해 메시지와 초기 설정값을 구분
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		TextMessage message = new TextMessage(obj.toJSONString());
		session.sendMessage(message);
		System.out.println(message.getPayload());
		/* session.sendMessage(new TextMessage(session.getId() + "님이 입장했습니다.")); */
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed : " + session + " : " + status);
		// 소켓 종료
		if(rls.size()>0) { //소켓이 종료되면 해당 세션값들을 찾아서 지움
			for(int i=0;i<rls.size();i++) {
				rls.get(i).remove(session.getId());
			}
		}
		/* session.sendMessage(new TextMessage(session.getId() + "님이 퇴장했습니다.")); */
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