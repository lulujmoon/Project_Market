package com.mm.market.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment/**")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	public List<CommentVO> commentList() throws Exception {
		return commentService.commentList();
	}

	public int commentInsert(CommentVO commentVO) throws Exception {
		return commentService.commentInsert(commentVO);
	}

	public int commentUpdate(CommentVO commentVO) throws Exception {
		return commentService.commentUpdate(commentVO);
	}

	public int commentDelete(long commentNum) throws Exception {
		return commentService.commentDelete(commentNum);
	}

}
