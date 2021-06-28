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
		
		return list;
	}
	
	public int countUnread(NotificationVO notificationVO) throws Exception {
		return notificationMapper.countUnread(notificationVO);
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
