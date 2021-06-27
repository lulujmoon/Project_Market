package com.mm.market.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.NoticePager;
import com.mm.market.util.Pager;

@Service
public class NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;

	public List<NoticeVO> getList(NoticePager noticePager)throws Exception{
		noticePager.makeRow();
		Long totalCount = noticeMapper.getTotalCount(noticePager);
		noticePager.makeNum(totalCount);
		return noticeMapper.getList(noticePager);
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
	
	public Long getPre(NoticeVO noticeVO)throws Exception{
		return noticeMapper.getPre(noticeVO);
	}
	
	public Long getNext(NoticeVO noticeVO)throws Exception{
		return noticeMapper.getNext(noticeVO);
	}
}
