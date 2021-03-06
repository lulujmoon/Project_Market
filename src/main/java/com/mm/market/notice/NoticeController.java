package com.mm.market.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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
		
		NoticeVO noticeVO2 = new NoticeVO();
		noticeVO2.setNoticeNum(noticeService.getPre(noticeVO));
		noticeVO2 = noticeService.getSelect(noticeVO2);
		
		NoticeVO noticeVO3 = new NoticeVO();
		noticeVO3.setNoticeNum(noticeService.getNext(noticeVO));
		noticeVO3=noticeService.getSelect(noticeVO3);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto",noticeVO);
		mv.addObject("pre",noticeVO2);
		mv.addObject("next",noticeVO3);
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
		mv.addObject("notice",noticeVO);
		mv.setViewName("notice/update");
		return mv;
	}
	
	@PostMapping("update")
	public String setUpdate(NoticeVO noticeVO)throws Exception{
		int result = noticeService.setUpdate(noticeVO);
		
		return "redirect:./list";
	}
	
	@PostMapping("delete")
	public String setDelete(NoticeVO noticeVO)throws Exception{
		
		int result = noticeService.setDelete(noticeVO);
		
		return "redirect:./list";
	}
	

}


