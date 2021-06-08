package com.mm.market.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentVO> commentList() throws Exception {
		return commentMapper.commentList();
	}

	public int commentInsert(CommentVO commentVO) throws Exception {
		return commentMapper.commentInsert(commentVO);
	}

	public int commentUpdate(CommentVO commentVO) throws Exception {
		return commentMapper.commentInsert(commentVO);
	}

	public int commentDelete(long commentNum) throws Exception {
		return commentMapper.commentDelete(commentNum);
	}

}
