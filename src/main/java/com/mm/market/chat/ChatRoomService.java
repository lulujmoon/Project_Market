package com.mm.market.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService implements ChatRoomMapper {

	@Autowired
	private ChatRoomMapper chatRoomMapper;

	@Value("${chat.filePath.txt}")
	private String filePath;
	
	
	@Override
	public void addChatRoom(ChatRoom chatRoom) throws Exception {
		Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
		chatRoom.setCreatedDate(createdDate);
		chatRoomMapper.addChatRoom(chatRoom);
		
	}
	
	
	
	//no connection with DB
	public List<ChatRoom> readChatHistory(ChatRoom chatRoom) throws Exception {
		String pathName = filePath+ chatRoom.getFileName();
		
		//DB에 저장된 txt파일 읽어오기
		BufferedReader br = new BufferedReader(new FileReader(pathName));
		//view에 ChatRoom 객체로 전달
		ChatRoom chatRoomLines = new ChatRoom();
		List<ChatRoom> chatHistory = new ArrayList<ChatRoom>();
		
		String chatLine;
		int idx = 1;
		
		while( (chatLine = br.readLine()) != null ) {
			
			//1개 메시지는 3줄로 구성 (보낸사람, 메시지내용, 보낸시간)
			int answer = idx % 3;
			
			if(answer == 1) {
				//보낸사람
				chatRoomLines.setSenderName(chatLine);
				idx++;
			} else if(answer == 2) {
				//메시지 내용
				chatRoomLines.setContent(chatLine);
				idx++;
			} else {
				//보낸시간
				chatRoomLines.setSendTime(chatLine);
				//메시지가 담긴 ChatRoom 객체 List에 저장
				chatHistory.add(chatRoomLines);
				//객체 초기화, 줄(row)인덱스 초기화
				chatRoomLines = new ChatRoom();
				idx=1;
			}
			
		}
		
		return chatHistory;
	}
	
	
	

	//no connection with DB
	public void appendMessage(ChatRoom chatRoom) throws Exception {
		Long productNum = chatRoom.getProductNum();
		String buyerId = chatRoom.getBuyerId();
		
		ChatRoom chatRoomAppend = chatRoomMapper.findByChatNum(productNum, buyerId);
		
		String pathName = filePath + chatRoomAppend.getFileName();
		
		//FileOutputStream은 무조건 파일을 생성, 존재하는 파일이 있을 경우 덮어쓰기(true)
		FileOutputStream fos = new FileOutputStream(pathName, true);
		String content = chatRoom.getContent();
		String senderName = chatRoom.getSenderName();
		String senderId = chatRoom.getSellerId();
		String sendTime = chatRoom.getSendTime();
		System.out.println("print : " + content);
		
		String writeContent = senderName + "\n" + content + "\n" + "[" + sendTime + "]" + "\n";
		
		byte [] b = writeContent.getBytes();
		
		fos.write(b);
		fos.close();
		
		System.out.println("senderId : " + senderId);
		System.out.println("sellerId : " + chatRoom.getSellerId());
		System.out.println(senderId.equals(chatRoom.getSellerId()));
		if(senderId.equals(chatRoom.getSellerId())) {
			updateChatReadBuy(chatRoom.getChatNum(), 0);
		} else {
			updateChatReadSell(chatRoom.getChatNum(), 0);
		}
		
		
	}

	@Override
	public ChatRoom findByChatNum(Long productNum, String buyerId) {
		return chatRoomMapper.findByChatNum(productNum, buyerId);
	}

	@Override
	public List<ChatList> findByUsername(String username) {
		return chatRoomMapper.findByUsername(username);
	}

	@Override
	public Long countByChatNum(Long chatNum, String buyerId) {
		return chatRoomMapper.countByChatNum(chatNum, buyerId);
	}

	@Override
	public Long getChatNum(Long productNum, String buyerId) {
		return chatRoomMapper.getChatNum(productNum, buyerId);
	}
	
	
	public void createFile(Long productNum, Long chatNum)throws Exception {
		String fileName= productNum+"_"+chatNum+".txt";
		String pathName= filePath+fileName;
		//File 클래스에 pathName 할당
		File txtFile = new File(pathName);
		//로컬경로에 파일 생성
		txtFile.createNewFile();
		
		chatRoomMapper.updateFileName(chatNum, fileName);
	}

	@Override
	public void updateFileName(Long chatNum, String fileName) {
		chatRoomMapper.updateFileName(chatNum, fileName);
	}

	@Override
	public void updateChatReadBuy(Long chatNum, int chatReadBuy) {
		chatRoomMapper.updateChatReadBuy(chatNum, chatReadBuy);
		
	}

	@Override
	public void updateChatReadSell(Long chatNum, int chatReadSell) {
		chatRoomMapper.updateChatReadSell(chatNum, chatReadSell);
	}

	@Override
	public int getUnreadMessage(String username) {
		return chatRoomMapper.getUnreadMessage(username);
	}

	@Override
	public List<Integer> getUnreadChatRoom(String username) {
		List<Integer> unread = chatRoomMapper.getUnreadChatRoom(username);
		return unread;
	}
	
	
	
}
