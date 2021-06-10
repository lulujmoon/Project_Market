package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.comment.CommentService;
import com.mm.market.comment.CommentVO;
import com.mm.market.socialCategory.SocialCategoryMapper;
import com.mm.market.socialCategory.SocialCategoryVO;
import com.mm.market.util.Pager;

@Controller
@RequestMapping("/social/**")
public class SocialController {

	@Autowired
	private SocialService socialService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private SocialCategoryMapper socialCategoryMapper;

	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception {
		List<SocialVO> ar = socialService.getList(pager);
		List<SocialCategoryVO> categories = socialCategoryMapper.getList();

		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		model.addAttribute("categories", categories);
		
		return "social/list";
	}

	@GetMapping("select")
	public ModelAndView getSelect(SocialVO socialVO, CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		socialVO = socialService.getSelect(socialVO);

		List<CommentVO> ar = commentService.getList(commentVO);

		mv.addObject("vo", socialVO);
		mv.addObject("comment", commentVO);
		mv.addObject("list", ar);

		mv.setViewName("social/select");

		return mv;
	}

	@GetMapping("insert")
	public ModelAndView setInsert() throws Exception {

		ModelAndView mv = new ModelAndView();
		SocialVO socialVO = new SocialVO();

		mv.addObject("vo", socialVO);
		mv.setViewName("social/insert");

		return mv;
	}

	@PostMapping("insert")
	public String setInsert(SocialVO socialVO, MultipartFile [] files, Model model) throws Exception {

		for(MultipartFile mf:files) {
			System.out.println(mf.getOriginalFilename());
		}

		int result = socialService.setInsert(socialVO, files);

		String message = "등록에 실패했습니다!";
		String path = "./list";

		if(result>0) {
			message = "등록에 성공했습니다!";
		}

		model.addAttribute("msg", message);
		model.addAttribute("path", path);

		return "common/commonResult";
	}

	@GetMapping("update")
	public ModelAndView setUpdate(SocialVO socialVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		socialVO = socialService.getSelect(socialVO);
		
		mv.addObject("vo", socialVO);
		mv.setViewName("social/update");

		return mv;
	}

	@PostMapping("update")
	public ModelAndView setUpdate(SocialVO socialVO, ModelAndView mv) throws Exception {
		
		int result = socialService.setUpdate(socialVO);
		
		//실행 O
		if(result>0) {
			System.out.println("수정 완료");
			mv.setViewName("redirect:./list");
		}
		
		//실행 X
		else {
			System.out.println("수정 실패");
			mv.setViewName("redirect:./list");
		}
		
		return mv;
	}

	@GetMapping("delete")
	public ModelAndView setDelete(SocialVO socialVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = socialService.setDelete(socialVO);

		String message = "삭제 실패";
		String path = "./list";

		if(result>0) {
			message = "삭제 성공!";
		}

		mv.addObject("msg", message);
		mv.addObject("path", path);
		
		mv.setViewName("common/commonResult");

		return mv;
	}

	//summerfile upload	
	@PostMapping("summerFileUpload")
	public ModelAndView setSummerFileUpload(MultipartFile file) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String fileName = socialService.setSummerFileUpload(file);
		fileName = "../resources/upload/social"+fileName;
		
		mv.addObject("result", fileName);
		mv.setViewName("common/ajaxResult");
		
		return mv;
		
	}
	
	@PostMapping("summerFileDelete")
	public ModelAndView setSummerFileDelete(String fileName) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean result = socialService.setSummerFileDelete(fileName);
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}

}
