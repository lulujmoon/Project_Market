package com.mm.market.notification;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {
	
	public List<NotificationVO> notiList(NotificationVO notificationVO) throws Exception;
	
	public NotificationVO notiSelect(NotificationVO notificationVO) throws Exception;
	
	public int countUnread(NotificationVO notificationVO) throws Exception;

	public int notiReadChk(NotificationVO notificationVO) throws Exception;
	
	public int notiInsert(NotificationVO notificationVO) throws Exception;
	
	public int notiDelete(NotificationVO notificationVO) throws Exception;
}