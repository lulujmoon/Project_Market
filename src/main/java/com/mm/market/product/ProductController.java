package com.mm.market.product;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import com.mm.market.category.CategoryMapper;
import com.mm.market.category.CategoryVO;
import com.mm.market.chat.ChatService;
import com.mm.market.chat.ChatVO;
import com.mm.market.location.LocationVO;
import com.mm.market.member.MemberFileVO;
import com.mm.market.member.MemberService;
import com.mm.market.member.MemberVO;
import com.mm.market.memberLocation.MemberLocationService;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.review.ReviewService;
import com.mm.market.review.ReviewVO;
import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;

@Controller
@RequestMapping("/product/**")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ChatService chatService;	

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberLocationService memberLocationService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("list")
	public String getList(ProductPager productPager, Long myLocation, Authentication authentication, Model model) throws Exception {

		if(myLocation == null) {
			myLocation = 0L;
		}
		
		if(authentication != null) {
			MemberVO memberVO = (MemberVO)authentication.getPrincipal();
			MemberLocationVO memberLocationVO = new MemberLocationVO();
			memberLocationVO.setUsername(memberVO.getUsername());
			
			List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
			memberLocationVO.setLocationCode(0L);
			locationList.add(0, memberLocationVO);
			
			productPager.setLocationCode(locationList.get(myLocation.intValue()).getLocationCode());
			model.addAttribute("locations", locationList);
		}

		List<ProductVO> productList  = productService.getList(productPager, 16L, 5L);

		List<CategoryVO> categories = categoryMapper.getList();


		model.addAttribute("products", productList);
		model.addAttribute("pager", productPager);
		model.addAttribute("myLocation", myLocation);
		model.addAttribute("categories", categories);

		return "product/list";
	}


	@GetMapping("select/{productNum}")
	public String getSelect(@PathVariable("productNum") Long productNum, Model model, Authentication authentication)throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(productNum);
		productVO =	productService.getSelect(productVO);


		if(authentication != null) {
			MemberVO memberVO = (MemberVO)authentication.getPrincipal();
			String username = memberVO.getUsername();
			
			HeartVO heartVO = new HeartVO();
			heartVO.setProductNum(productNum);
			heartVO.setUsername(username);
			
			Long heart = productService.getHeart(heartVO);
			
			model.addAttribute("heart", heart);			
		}

		model.addAttribute("product", productVO);

		//판매자 정보
		if(productVO.getUsername() != null) {
			MemberVO sellerVO = new MemberVO();
			sellerVO.setUsername(productVO.getUsername());
			sellerVO = memberService.getSeletByUsername(sellerVO);
			MemberFileVO sellerFileVO = new MemberFileVO();
			sellerFileVO = memberService.selectFile(sellerVO);
			MemberLocationVO sellerLocationVO = new MemberLocationVO();
			sellerLocationVO.setUsername(sellerVO.getUsername());
			List<MemberLocationVO> sellerLocations = memberLocationService.getList(sellerLocationVO);
			
			ReviewVO reviewVO = new ReviewVO();
			reviewVO.setReviewee(sellerVO.getUsername());
			reviewVO = reviewService.getAvgsAndCounts(reviewVO);
		

			model.addAttribute("seller", sellerVO);
			model.addAttribute("sellerFile", sellerFileVO);
			model.addAttribute("sellerLocation", sellerLocations.get(0));

			//chat
			if(authentication != null) {
				ChatVO chatVO = new ChatVO();
				MemberVO memberVO = (MemberVO)authentication.getPrincipal();
				String username = memberVO.getUsername();
				chatVO.setUsername(username);
				System.out.println("@chatVO.getUsername : "+chatVO.getUsername());
				List<ChatVO> list = chatService.chatList(chatVO);
				System.out.println("list : "+list);
				
				if(list.size()<1) {
					model.addAttribute("chat", 0);
				} else {
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getOtherUser() == sellerVO.getUsername()) {
							model.addAttribute("chat", list.get(i).getOtherUser());
						} else {
							model.addAttribute("chat", 0);
						}
					}
				}
			}
			model.addAttribute("rating", reviewVO);
		}

		return "product/select";
	}

	@ResponseBody
	@PostMapping(value="heart", produces = "application/json")
	public Long heart(HttpServletRequest httpServletRequest, Authentication auth)throws Exception{

		Long heart = Long.parseLong(httpServletRequest.getParameter("heart"));
		Long productNum =Long.parseLong(httpServletRequest.getParameter("productNum"));

		int startInx = auth.getPrincipal().toString().indexOf("=");
		int lastInx = auth.getPrincipal().toString().indexOf(",");

		String username = auth.getPrincipal().toString().substring(startInx+1, lastInx);
		System.out.println(username);

		HeartVO heartVO = new HeartVO();

		heartVO.setProductNum(productNum);
		heartVO.setUsername(username);


		if(heart >= 1 ) {
			productService.deleteHeart(heartVO);
			heart=0L;
		} else {
			productService.setHeart(heartVO);
			heart=1L;
		}

		System.out.println("HEART : "+heart);

		return heart;
	}



	@PostMapping("delete/{productNum}")
	public ModelAndView setDelete(@PathVariable("productNum") Long productNum)throws Exception{
		ModelAndView mv = new ModelAndView();
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(productNum);
		productVO = productService.getSelect(productVO);
		System.out.println(productVO);
		int result = productService.setDelete(productVO);

		String message="글이 삭제되지 않았습니다. 다시 시도해주세요.";
		String path = "./list";

		if(result>0) {
			message="글이 삭제되었습니다.";
		}

		mv.addObject("msg", message);
		mv.addObject("path", path);
		mv.setViewName("common/commonResult");

		return mv;
	}

	@PostMapping("fileDelete")
	public ModelAndView setFileDelete(ProductFileVO productFileVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = productService.setFileDelete(productFileVO);
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");

		return mv;
	}



	@GetMapping("insert")
	public void setInsert(Authentication authentication, Model model) throws Exception {

		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
		
		List<CategoryVO> categories = categoryMapper.getList();

		model.addAttribute("location", locationList);
		model.addAttribute("categories", categories);
	}

	@PostMapping("insert")
	public String setInsert(ProductVO productVO, MultipartFile [] file) throws Exception {	
		productService.setInsert(productVO, file);

		return "redirect:./list";
	}


	@GetMapping("update/{productNum}")
	public String setUpdate(@PathVariable("productNum")Long productNum, Authentication authentication, Model model)throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(productNum);
		productVO = productService.getSelect(productVO);
		
		List<ProductFileVO> files = productVO.getFiles();
		for(int i=0;i<files.size();i++) {
			files.get(i).setProductNum(productVO.getProductNum());
		}

		List<CategoryVO> categories = categoryMapper.getList();

		MemberLocationVO memberLocationVO = new MemberLocationVO();
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO)authentication.getPrincipal();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locations = memberLocationService.getList(memberLocationVO);
		
		model.addAttribute("product", productVO);
		model.addAttribute("files", files);
		model.addAttribute("categories", categories);
		model.addAttribute("locations", locations);

		return "product/update";
	}

	@PostMapping("update")
	public String setUpdate(ProductVO productVO, MultipartFile [] file)throws Exception{

		productService.setUpdate(productVO, file);
		productVO = productService.getSelect(productVO);

		return "redirect:./list";
	}


	@GetMapping("rewrite")
	public ModelAndView setRewrite(ProductVO productVO, Authentication authentication, ModelAndView mv) throws Exception{
		productVO = productService.getSelect(productVO);

		long write = productVO.getProductDate().getTime();

		long end = System.currentTimeMillis();
		/*
		 * Timestamp dateEnd = new Timestamp(end); Timestamp dateWrite = new
		 * Timestamp(write); System.out.println("작성시간 : " +dateWrite);
		 * System.out.println("현재시간 : " +dateEnd);
		 */
		long gap = 259220-(end - write)/1000;
		/*
		 * System.out.println("write : "+write); System.out.println("end : "+end);
		 * System.out.println("글쓰고 초 :" + gap);
		 */
		if(gap>0) {
			long day = gap/(60*60*24);
			long hour = (gap - day *60*60*24) / (60*60);
			long minute = (gap - day  * 60*60*24 - hour*3600) / 60;
			long second = gap%60;

			System.out.println(day+"일"+hour+"시간"+minute+"분"+second+"초");



			mv.addObject("msg", ""+day+"일"+hour+"시간"+minute+"분"+second+"초 이후에 끌올이 가능해요!");
			mv.addObject("path", "select/"+productVO.getProductNum());
			mv.setViewName("common/commonResult");
		} else {

			List<ProductFileVO> files = productVO.getFiles();

			for(ProductFileVO file:files) {
				file.setProductNum(productVO.getProductNum());
			}
			
			List<CategoryVO> categories = categoryMapper.getList();

			MemberLocationVO memberLocationVO = new MemberLocationVO();
			MemberVO memberVO = new MemberVO();
			memberVO = (MemberVO)authentication.getPrincipal();
			memberLocationVO.setUsername(memberVO.getUsername());
			List<MemberLocationVO> locations = memberLocationService.getList(memberLocationVO);
			
			mv.addObject("files", files);
			mv.addObject("product", productVO);
			mv.addObject("categories", categories);
			mv.addObject("locations", locations);
			mv.setViewName("product/rewrite");
		}

		return mv;
	}

	@PostMapping("rewrite")
	public String setRewrite(ProductVO productVO, MultipartFile [] file)throws Exception{
		productService.setRewrite(productVO, file);
		productVO = productService.getSelect(productVO);

		return "redirect:./list";
	}
	
	
	@PostMapping("setStatus")
	public String setStatus(ProductVO productVO)throws Exception{
		productService.setStatus(productVO);
		productVO=productService.getSelect(productVO);
		String url ="";
		
		String status = productVO.getProductStatus();

		if(status.equals("예약 중")) {
			url = "redirect:/chat/chatList2?productNum="+productVO.getProductNum()+"&&locationCode="+productVO.getLocationCode();
		}else if(status.equals("판매완료")) {
			url = "redirect:/review/insert?productNum="+productVO.getProductNum();
		}else if(status.equals("판매 중")) {
			url = "redirect:select/"+productVO.getProductNum();
		}
		
		System.out.println(url);
		
		return url;
	}
	
	//가격 제안하기 페이지
	@GetMapping("nego")
	public void setNego(ProductVO productVO, Authentication auth)throws Exception{
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		
		productVO = productService.getSelect(productVO);
		Long productNum = productVO.getProductNum();
		
		String seller = productVO.getUsername();
		System.out.println("sendUser: "+username);
		System.out.println("productNum: "+productNum);
		System.out.println("seller: "+seller);
	}
}
