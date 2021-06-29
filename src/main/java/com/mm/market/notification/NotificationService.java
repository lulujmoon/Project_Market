package com.mm.market.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.NotificationPager;

@Service
public class NotificationService {

	@Autowired
	private NotificationMapper notificationMapper;
	
	public List<NotificationVO> notiList(NotificationPager pager) throws Exception{
		pager.setPerPage(20L);
		pager.makeRow(20L);
		Long totalCount = notificationMapper.getTotalCount(pager);
		pager.makeNum(totalCount, 20L, 5L);
	
		return notificationMapper.notiList(pager);
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
