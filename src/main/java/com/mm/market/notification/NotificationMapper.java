package com.mm.market.notification;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.NotificationPager;

@Mapper
public interface NotificationMapper {
	
	public List<NotificationVO> notiList(NotificationPager notificationPager) throws Exception;
	
	public Long getTotalCount(NotificationPager notificationPager) throws Exception;
	
	public NotificationVO notiSelect(NotificationVO notificationVO) throws Exception;
	
	public int countUnread(NotificationVO notificationVO) throws Exception;

	public int notiReadChk(NotificationVO notificationVO) throws Exception;
	
	public int notiInsert(NotificationVO notificationVO) throws Exception;
	
	public int notiDelete(NotificationVO notificationVO) throws Exception;
}