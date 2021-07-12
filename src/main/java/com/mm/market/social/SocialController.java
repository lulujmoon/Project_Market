package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.comment.CommentService;
import com.mm.market.comment.CommentVO;
import com.mm.market.member.MemberVO;
import com.mm.market.memberLocation.MemberLocationService;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.socialCategory.SocialCategoryMapper;
import com.mm.market.socialCategory.SocialCategoryVO;
import com.mm.market.util.SocialPager;

@Controller
@RequestMapping("/social/**")
public class SocialController {

	@Autowired
	private SocialService socialService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private SocialCategoryMapper socialCategoryMapper;

	@Autowired
	private MemberLocationService memberLocationService;
	
	@GetMapping("list")
	public String getList(SocialPager socialPager, Long myLocation, Authentication auth, Model model) throws Exception {
		if(myLocation == null) {
			myLocation = 0L;
		}
		
		if(auth != null) {
			MemberVO memberVO = (MemberVO)auth.getPrincipal();
			MemberLocationVO memberLocationVO = new MemberLocationVO();
			memberLocationVO.setUsername(memberVO.getUsername());
			
			List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
			memberLocationVO.setLocationCode(0L);
			locationList.add(0, memberLocationVO);
			
			socialPager.setLocationCode(locationList.get(myLocation.intValue()).getLocationCode());
			model.addAttribute("locations", locationList);
		}

		List<SocialVO> ar = socialService.getList(socialPager);
		List<SocialCategoryVO> categories = socialCategoryMapper.getList();

		model.addAttribute("list", ar);
		model.addAttribute("socialPager", socialPager);
		model.addAttribute("myLocation", myLocation);
		model.addAttribute("categories", categories);
		
		return "social/list";
	}

	@GetMapping("select/{socialNum}")
	public ModelAndView getSelect(@PathVariable Long socialNum, SocialVO socialVO, CommentVO commentVO, Authentication auth) throws Exception {
		ModelAndView mv = new ModelAndView();

		socialVO.setSocialNum(socialNum);
		socialVO = socialService.getSelect(socialVO);
		
		if(auth != null) {
			MemberVO memberVO = (MemberVO)auth.getPrincipal();
			String username = memberVO.getUsername();
			
			GoodVO goodVO = new GoodVO();
			goodVO.setSocialNum(socialNum);
			goodVO.setUsername(username);
			
			Long good = socialService.getGood(goodVO);
			
			mv.addObject("good", good);
		}

		List<CommentVO> commentList = commentService.getList(commentVO);

		mv.addObject("social", socialVO);
		mv.addObject("comments", commentList);

		mv.setViewName("social/select");

		return mv;
	}

	@GetMapping("insert")
	public void setInsert(Model model, Authentication authentication) throws Exception {
		List<SocialCategoryVO> categoryList = socialCategoryMapper.getList();
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);

		model.addAttribute("categories", categoryList);
		model.addAttribute("locations", locationList);
	}

	@PostMapping("insert")
	public String setInsert(SocialVO socialVO, Model model) throws Exception {

		int result = socialService.setInsert(socialVO);
		
		String message = "등록에 실패했습니다.";
		String path = "./list";

		if(result>0) {
			message = "글이 등록되었습니다.";
			path = "./select/"+socialService.getSocialNum();
		}

		model.addAttribute("msg", message);
		model.addAttribute("path", path);

		return "common/commonResult";
	}

	@GetMapping("update/{socialNum}")
	public ModelAndView setUpdate(@PathVariable Long socialNum) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		SocialVO socialVO = new SocialVO();
		socialVO.setSocialNum(socialNum);
		
		socialVO = socialService.getSelect(socialVO);
		List<SocialCategoryVO> categories = socialCategoryMapper.getList();
		
		mv.addObject("social", socialVO);
		mv.addObject("categories", categories);
		mv.setViewName("social/update");

		return mv;
	}

	@PostMapping("update")
	public ModelAndView setUpdate(SocialVO socialVO, ModelAndView mv) throws Exception {
		
		int result = socialService.setUpdate(socialVO);
		
		//실행 O
		if(result>0) {
			System.out.println("수정 완료");
			mv.setViewName("redirect:./select/"+socialVO.getSocialNum());
		}
		
		//실행 X
		else {
			System.out.println("수정 실패");
			mv.setViewName("redirect:./list");
		}
		
		return mv;
	}

	@PostMapping("delete")
	public String setDelete(SocialVO socialVO, Model model) throws Exception {
		int result = socialService.setDelete(socialVO);
		model.addAttribute("result", result);
		
		return "/common/ajaxResult";
	}
	
	@ResponseBody
	@PostMapping(value = "good", produces = "application/json")
	public Long good(HttpServletRequest request, Authentication auth) throws Exception {
		Long good = Long.parseLong(request.getParameter("good"));
		Long socialNum = Long.parseLong(request.getParameter("socialNum"));

		int startInx = auth.getPrincipal().toString().indexOf("=");
		int lastInx = auth.getPrincipal().toString().indexOf(",");

		String username = auth.getPrincipal().toString().substring(startInx+1, lastInx);

		GoodVO goodVO = new GoodVO();
		
		goodVO.setSocialNum(socialNum);
		goodVO.setUsername(username);
		
		if(good>=1) {
			socialService.deleteGood(goodVO);
			good=0L;
		} else {
			socialService.insertGood(goodVO);
			good=1L;
		}

		return good;
	}
	
	//summerfile upload	
	@PostMapping("summerFileUpload")
	public ModelAndView setSummerFileUpload(MultipartFile file) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		String fileName = socialService.setSummerFileUpload(file);
		fileName = "/resources/upload/social/"+fileName;
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