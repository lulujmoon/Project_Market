package com.mm.market.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	
	//댓글 개수
	public int commentCount() throws Exception;
	//List
	public List<CommentVO> commentList() throws Exception;
	//Insert
	public int commentInsert(CommentVO commentVO) throws Exception;
	//Update
	public int commentUpdate(CommentVO commentVO) throws Exception;
	//Delete
	public int commentDelete(long commentNum) throws Exception;

}
