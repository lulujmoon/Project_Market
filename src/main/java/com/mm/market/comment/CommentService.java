package com.mm.market.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentVO> getList(CommentVO commentVO) throws Exception {
		return commentMapper.getList(commentVO);
	}

	public CommentVO getSelect(CommentVO commentVO) throws Exception{
		return commentMapper.getSelect(commentVO);
	}

	public int setInsert(CommentVO commentVO) throws Exception {
		return commentMapper.setInsert(commentVO);
	}

	public int setUpdate(CommentVO commentVO) throws Exception {
		return commentMapper.setUpdate(commentVO);
	}

	public int setDelete(CommentVO commentVO) throws Exception {
		return commentMapper.setDelete(commentVO);
	}

}
