package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	public String getSelect(SocialVO socialVO, Model model) throws Exception {
		socialVO = socialService.getSelect(socialVO);
		model.addAttribute("vo", socialVO);

		return "social/select";
	}
	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(SocialVO socialVO, MultipartFile [] file) throws Exception {
		int result = socialService.setInsert(socialVO, file);

		return "redirect:./list";
	}

	@GetMapping("update")
	public String setUpdate(SocialVO socialVO, Model model) throws Exception {
		socialVO = socialService.getSelect(socialVO);
		List<SocialFileVO> file = socialVO.getFiles();

		for(int i=0;i<file.size();i++) {
			file.get(i).setSocialNum(socialVO.getSocialNum());
		}
		
		model.addAttribute("vo", socialVO);
		model.addAttribute("file", file);
		
		return "social/update";
	}

	@PostMapping("update")
	public String setUpdate(SocialVO socialVO, MultipartFile file) throws Exception {
		socialService.setUpdate(socialVO, file);
		socialVO = socialService.getSelect(socialVO);
		
		return "redirect:./list";
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
	
	@GetMapping("fileDelete")
	public ModelAndView setFileDelete(SocialFileVO socialFileVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = socialService.setFileDelete(socialFileVO);
		mv.addObject("result", result);
		mv.addObject("msg", "삭제하시겠습니까?");
		mv.setViewName("common/commonResult");
		
		return mv;
	}

}
