package com.mm.market.chat;

import lombok.Data;

@Data
public class RoomVO {
	
	int roomNumber;
	String roomName;
	
	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomName =" + roomName + "]";
	}

}
