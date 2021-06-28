package com.mm.market.notification;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class NotificationMapper {
	
	public int notiInsert(NotificationVO notificationVO)throws Exception;
	
	public int countUnread(NotificationVO notificationVO)throws Exception;
	
	public int notiReadChk(NotificationVO notificationVO)throws Exception;
	
	public int notiDelete(NotificationVO notificationVO)throws Exception;
	
	
}
