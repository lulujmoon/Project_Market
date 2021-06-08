package com.mm.market.product;

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

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.category.CategoryMapper;
import com.mm.market.category.CategoryVO;
import com.mm.market.member.MemberVO;
import com.mm.market.memberLocation.MemberLocationService;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;

@Controller
@RequestMapping("/product/**")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private MemberLocationService memberLocationService;
	
	@GetMapping("list")
	public String getList(ProductPager productPager, Long myLocation, Authentication authentication, Model model) throws Exception {
		
		if(myLocation == null) {
			myLocation = -1L;
		}
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
		List<ProductVO> productList = null;
		
		if(myLocation>=0L) {			
			productPager.setLocationCode(locationList.get(myLocation.intValue()).getLocationCode());
			productList = productService.getList(productPager, 16L, 5L);			
		}else {
			productPager.setLocationCode(0L);
			productList = productService.getList(productPager, 16L, 5L);			
		}
		
		List<CategoryVO> categories = categoryMapper.getList();
		
		
		model.addAttribute("products", productList);
		model.addAttribute("pager", productPager);
		model.addAttribute("myLocation", myLocation);
		model.addAttribute("categories", categories);
		model.addAttribute("locations", locationList);
		
		return "product/list";
	}
	
	
	@GetMapping("select")
	public String getSelect(@RequestParam("productNum") Long productNum ,ProductVO productVO, Model model, Authentication auth)throws Exception {
		productVO = productService.getSelect(productVO);
		System.out.println(productVO);
		
		int startInx = auth.getPrincipal().toString().indexOf("=");
		int lastInx = auth.getPrincipal().toString().indexOf(",");
		
		String username = auth.getPrincipal().toString().substring(startInx+1, lastInx);
		System.out.println(username);
		
		HeartVO heartVO = new HeartVO();
		heartVO.setProductNum(productNum);
		heartVO.setUsername(username);
		
		Long heart = productService.getHeart(heartVO);
		System.out.println("Heart : "+heart);
		
		
		model.addAttribute("heart", heart);
		model.addAttribute("vo", productVO);
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
	
	
	
	@GetMapping("delete")
	public ModelAndView setDelete(ProductVO productVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println(productVO);
		int result = productService.setDelete(productVO);
	//	System.out.println(productVO);
			
		String message="삭제 실패";
		String path = "./list";
		
		if(result>0) {
			message="삭제 성공!";
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
		mv.addObject("msg", "정말 삭제하시겠습니까?");
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
	
	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(ProductVO productVO, MultipartFile [] file) throws Exception {
		
		int result = productService.setInsert(productVO, file);

		return "redirect:./list";
	}
	
	
	@PostMapping("summerFileDelete")
	public ModelAndView setSummerFileDelete(String fileName) throws Exception {
		ModelAndView mv= new ModelAndView();
		boolean result = productService.setSummerFileDelete(fileName);
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	@PostMapping("summerFileUpload")
	public ModelAndView setSummerFileUpload(MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("Summer File Upload");
		System.out.println(file.getOriginalFilename()); 
		String fileName = productService.setSummerFileUpload(file);
		fileName = "../resources/upload/product/"+fileName;
		mv.addObject("result", fileName);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	@GetMapping("update")
	public String setUpdate(ProductVO productVO, Model model)throws Exception{
		productVO = productService.getSelect(productVO);
		List<ProductFileVO> file = productVO.getFiles();

		for(int i=0;i<file.size();i++) {
			file.get(i).setProductNum(productVO.getProductNum());
		}

		model.addAttribute("vo", productVO);
		model.addAttribute("files", file);

		System.out.println(file);

		return "product/update";
	}
	
	@PostMapping("update")
	public String setUpdate(ProductVO productVO, MultipartFile file)throws Exception{
		productService.setUpdate(productVO, file);
		productVO = productService.getSelect(productVO);
				
		return "redirect:./list";
	}
}
