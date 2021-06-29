package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping("select")
	public ModelAndView getSelect(@RequestParam("socialNum") Long socialNum, SocialVO socialVO, CommentVO commentVO, Authentication auth) throws Exception {
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

		List<CommentVO> ar = commentService.getList(commentVO);

		mv.addObject("social", socialVO);
		mv.addObject("comment", commentVO);
		mv.addObject("list", ar);

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

}