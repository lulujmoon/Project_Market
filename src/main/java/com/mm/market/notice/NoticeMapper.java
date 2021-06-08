package com.mm.market.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	
	public List<NoticeVO> getList()throws Exception;
	
	public NoticeVO getSelect(NoticeVO noticeVO)throws Exception;
	
	public int setInsert(NoticeVO noticeVO)throws Exception;

	public int setUpdate(NoticeVO noticeVO)throws Exception;
	
	public int setDelete(NoticeVO noticeVO)throws Exception;
}
