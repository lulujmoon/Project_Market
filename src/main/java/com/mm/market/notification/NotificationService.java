package com.mm.market.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationMapper notificationMapper;
	
	public List<NotificationVO> notiList(NotificationVO notificationVO) throws Exception{
		
		String username = notificationVO.getNotiRecvUser();
		
		List<NotificationVO> list = notificationMapper.notiList(notificationVO);
		
		for(NotificationVO noti : list) {
			noti.setNotiRecvUser(username);
			int unread = notificationMapper.countUnread(notificationVO);
			noti.setUnread(unread);
		}
		
		return list;
	}
	
	public int notiReadChk(NotificationVO notificationVO) throws Exception {
		return notificationMapper.notiReadChk(notificationVO);
	}
	
	public NotificationVO notiSelect(NotificationVO notificationVO) throws Exception {
		return notificationMapper.notiSelect(notificationVO);
	}
	
	public int notiInsert(NotificationVO notificationVO) throws Exception {
		return notificationMapper.notiInsert(notificationVO);
	}
	
	
	public int notiDelete(NotificationVO notificationVO) throws Exception {
		return notificationMapper.notiDelete(notificationVO);
	}

}
