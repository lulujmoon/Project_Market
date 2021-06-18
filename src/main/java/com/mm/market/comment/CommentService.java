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
	
	public int setReply(CommentVO commentVO) throws Exception{
		
		//부모 글의 ref, step, depth 조회
		CommentVO parent = commentMapper.getSelect(commentVO);
		
		commentVO.setRef(parent.getRef());
		commentVO.setStep(parent.getStep()+1);
		commentVO.setDepth(parent.getDepth()+1);
		
		int result = commentMapper.setReplyUpdate(parent);
		result = commentMapper.setReply(commentVO);
		
		return result;
	}


}
