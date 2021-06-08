package com.mm.market.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;

	public List<NoticeVO> getList()throws Exception{
		return noticeMapper.getList();
	}
	
	public NoticeVO getSelect(NoticeVO noticeVO)throws Exception{
		return noticeMapper.getSelect(noticeVO);
	}
	
	public int setInsert(NoticeVO noticeVO)throws Exception{
		return noticeMapper.setInsert(noticeVO);
	}
	
	public int setUpdate(NoticeVO noticeVO)throws Exception{
		return noticeMapper.setUpdate(noticeVO);
	}
	
	public int setDelete(NoticeVO noticeVO)throws Exception{
		return noticeMapper.setDelete(noticeVO);
	}
}
