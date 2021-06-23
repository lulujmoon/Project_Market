package com.mm.market.chat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@EqualsAndHashCode(of = "chatId")
@Builder @AllArgsConstructor @NoArgsConstructor
public class ChatVO {
	@Id 
	private Long chatId;
	private Long productNum;
	private String sender;
	private String message;
	private LocalDateTime sendDateTime;
	
}
