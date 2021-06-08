package com.mm.market.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.NoticePager;
import com.mm.market.util.Pager;

@Mapper
public interface NoticeMapper {
	
	public List<NoticeVO> getList(NoticePager noticePager)throws Exception;
	
	public Long getTotalCount(NoticePager noticePager)throws Exception;
	
	public NoticeVO getSelect(NoticeVO noticeVO)throws Exception;
	
	public int setInsert(NoticeVO noticeVO)throws Exception;

	public int setUpdate(NoticeVO noticeVO)throws Exception;
	
	public int setDelete(NoticeVO noticeVO)throws Exception;
}
