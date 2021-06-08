package com.mm.market.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.util.NoticePager;
import com.mm.market.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv,NoticePager noticePager)throws Exception{
		List<NoticeVO>list=noticeService.getList(noticePager);
		mv.addObject("list",list);
		mv.addObject("noticePager",noticePager);
		mv.setViewName("notice/list");
		
		return mv;
	}
	
	@GetMapping("select")
	public ModelAndView getSelect(NoticeVO noticeVO)throws Exception{
		noticeVO =noticeService.getSelect(noticeVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto",noticeVO);
		mv.setViewName("notice/select");
		
		return mv;
	}
	
	@GetMapping("insert")
	public void setInsert()throws Exception{
		
	}
	
	@PostMapping("insert")
	public String setInsert(NoticeVO noticeVO)throws Exception{
		int result =noticeService.setInsert(noticeVO);
		
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(NoticeVO noticeVO,ModelAndView mv)throws Exception{
		noticeVO =noticeService.getSelect(noticeVO);
		mv.addObject("dto",noticeVO);
		mv.setViewName("notice/update");
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(NoticeVO noticeVO)throws Exception{
		int result = noticeService.setUpdate(noticeVO);
		
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(NoticeVO noticeVO)throws Exception{
		int result = noticeService.setDelete(noticeVO);
		
		return "redirect:./list";
	}

}


