package com.mm.market.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationMapper notificationMapper;
	 
	public int notiInsert(NotificationVO notificationVO) throws Exception {
		int result = notificationMapper.notiInsert(notificationVO);
		
		return result;
	}
		
	public int notiDelete(NotificationVO notificationVO) throws Exception {
		int result = notificationMapper.notiDelete(notificationVO);
		return result;
		
	}
	
	
	
}
