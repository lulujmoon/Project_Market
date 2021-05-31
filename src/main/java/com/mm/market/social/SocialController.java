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

import com.mm.market.util.Pager;

@Controller
@RequestMapping("/social/**")
public class SocialController {
	
	@Autowired
	private SocialService socialService;
	
	@GetMapping("list")
	public String getList(Model model, Pager pager) throws Exception {
		List<SocialVO> ar = socialService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);

		return "social/list";
	}
	
	@GetMapping("select")
	public ModelAndView getSelect(SocialVO socialVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		socialVO = socialService.getSelect(socialVO);
		mv.addObject("vo", socialVO);
		mv.setViewName("social/select");
		return mv;
	}
	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(SocialVO socialVO) throws Exception {
		int result = socialService.setInsert(socialVO);
		return "redirect:./list";
	}

	@GetMapping("update")
	public void setUpdate() throws Exception {}

	@PostMapping("update")
	public String setUpdate(SocialVO socialVO) throws Exception {
		int result = socialService.setUpdate(socialVO);
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(SocialVO socialVO) throws Exception {
		int result = socialService.setDelete(socialVO);
		return "redirect:./list";
	}
}
