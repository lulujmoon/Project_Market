package com.mm.market.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment/**")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("insert")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		CommentVO commentVO = new CommentVO();
		
		mv.addObject("vo", commentVO);
		mv.setViewName("social/select");
		
		return mv;
	}

	@PostMapping("insert")
	public ModelAndView setInsert(CommentVO commentVO) throws Exception {

		ModelAndView mv = new ModelAndView();
		int result = commentService.setInsert(commentVO);
		
		mv.setViewName("redirect:../social/list");
		
		return mv;
	}
	
	@GetMapping("update") //댓글 수정
	public ModelAndView setUpdate(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		commentVO = commentService.getSelect(commentVO);

		mv.addObject("comment", commentVO);
		mv.setViewName("comment/update");

		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setUpdate(CommentVO commentVO, ModelAndView mv) throws Exception {
		int result = commentService.setUpdate(commentVO);

		if(result>0) {
			System.out.println("수정 완료!");
			mv.setViewName("redirect:../social/list");
		}
		
		else {
			System.out.println("수정 실패");
			mv.setViewName("redirect:../social/list");
		}
		
		return mv;
	}

	@GetMapping("delete")
	public ModelAndView commentDelete(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = commentService.setDelete(commentVO);
		String message = "삭제 실패";
		String path = "../social/list";
		
		if(result>0) {
			message = "삭제 성공";
		}
		
		mv.addObject("msg", message);
		mv.addObject("path", path);
		
		mv.setViewName("common/commonResult");
		
		return mv;
	}

	@GetMapping("reply")
	public ModelAndView setReply() throws Exception {
		ModelAndView mv = new ModelAndView();
		CommentVO commentVO = new CommentVO();
		
		mv.setViewName("comment/reply");
		
		return mv;
	}

	@PostMapping("reply")
	public ModelAndView setReply(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = commentService.setReply(commentVO);
		
		mv.setViewName("redirect:../social/list");
		
		return mv;
	}
}
