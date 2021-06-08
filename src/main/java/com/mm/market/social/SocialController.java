package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	public String setInsert(SocialVO socialVO, MultipartFile [] files, HttpSession session) throws Exception {
		int result = socialService.setInsert(socialVO, files, session);

		return "redirect:./list";
	}

	@GetMapping("update")
	public String setUpdate(SocialVO socialVO, Model model) throws Exception {
		socialVO = socialService.getSelect(socialVO);
		model.addAttribute("vo", socialVO);
		return "social/update";
	}

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
