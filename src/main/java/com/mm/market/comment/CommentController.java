package com.mm.market.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.member.MemberVO;

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
	public String setInsert(CommentVO commentVO) throws Exception {
		commentService.setInsert(commentVO);
		
		return "redirect:../social/select/"+commentVO.getSocialNum();
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
	public String setUpdate(CommentVO commentVO, Authentication authentication, ModelAndView mv) throws Exception {
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		commentVO.setUsername(memberVO.getUsername());		
		commentService.setUpdate(commentVO);
		
		commentVO = commentService.getSelect(commentVO);
		
		return "redirect:../social/select/"+commentVO.getSocialNum();
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
	public String setReply(CommentVO commentVO, Authentication authentication) throws Exception {
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		commentVO.setUsername(memberVO.getUsername());
		commentService.setReply(commentVO);
		
		commentVO = commentService.getSelect(commentVO);
		
		return "redirect:../social/select/"+commentVO.getSocialNum();
	}
}
