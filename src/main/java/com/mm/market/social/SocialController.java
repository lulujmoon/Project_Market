package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Value("${social.filePath}")
	public String filePath;
	
	@ModelAttribute("social")
	public String getSocial() {
		return "social";
	}

	@GetMapping("fileDown")
	public ModelAndView fileDown(String fileName, String originName) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileName", fileName);
		mv.addObject("originName", originName);
		mv.addObject("filePath", filePath);
		mv.setViewName("fileDown");
		mv.setViewName("down");

		// /fileDown.html
		return mv;
	}

	@GetMapping("list")
	public String getList(Model model, Pager pager) throws Exception {
		System.out.println("FilePath : "+filePath);
		List<SocialVO> ar = socialService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		System.out.println(pager.getStartNum());
		System.out.println(pager.getLastNum());

		// /board/list.html
		return "board/list";
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
	public String setInsert(Model model) throws Exception {
		model.addAttribute("vo", new SocialVO());
		model.addAttribute("action", "insert");
		return "social/form";
	}
	
	@PostMapping("insert")
	public String setInsert(SocialVO socialVO, MultipartFile [] files) throws Exception {
		int result = socialService.setInsert(socialVO, files);
		return "redirect:./list";
	}

	@GetMapping("update")
	public String setUpdate(SocialVO socialVO, Model model) throws Exception {
		socialVO = socialService.getSelect(socialVO);
		model.addAttribute("vo", socialVO);
		model.addAttribute("action", "update");
		return "board/form";
	}

	@PostMapping("update")
	public String setUpdate() throws Exception {
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(SocialVO socialVO) throws Exception {
		int result = socialService.setDelete(socialVO);
		return "redirect:./list";
	}
}
