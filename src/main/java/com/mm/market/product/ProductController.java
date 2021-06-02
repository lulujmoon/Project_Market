package com.mm.market.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.category.CategoryMapper;
import com.mm.market.category.CategoryVO;
import com.mm.market.util.Pager;

@Controller
@RequestMapping("/product/**")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@GetMapping("list")
	public String getList( Pager pager, Model model) throws Exception {
		
		List<ProductVO> ar = productService.getList(pager);
		List<CategoryVO> categories = categoryMapper.getList();

		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		model.addAttribute("categories", categories);
		
		return "product/list";
	}
	
	
	@GetMapping("select")
	public String getSelect(@RequestParam("productNum") Long productNum ,ProductVO productVO, Model model, HttpServletRequest httpServletRequest)throws Exception {
		productVO = productService.getSelect(productVO);
		System.out.println(productVO);
		
		String username = String.valueOf(httpServletRequest.getSession().getAttribute("username"));
		
		HeartVO heartVO = new HeartVO();
		heartVO.setProductNum(productNum);
		heartVO.setUsername(username);
		
		Long heart = productService.getHeart(heartVO);
		System.out.println("Heart : "+heart);
		
		
		model.addAttribute("heart", heart);
		
		model.addAttribute("vo", productVO);
		return "product/select";
	}
	
	@PostMapping(value="heart", produces = "application/json")
	public Long heart(HttpServletRequest httpServletRequest)throws Exception{
		
		Long heart = Long.parseLong(httpServletRequest.getParameter("heart"));
		Long productNum =Long.parseLong(httpServletRequest.getParameter("productNum"));
		String username = String.valueOf(httpServletRequest.getSession().getAttribute("username"));
		
		HeartVO heartVO = new HeartVO();
		
		heartVO.setProductNum(productNum);
		heartVO.setUsername(username);
		
		System.out.println(heart);
		
		if(heart >= 1 ) {
			productService.deleteHeart(heartVO);
			heart=0L;
		} else {
			productService.setHeart(heartVO);
			heart=1L;
		}
		
		return heart;
	}
	
	
	
	@GetMapping("delete")
	public String setDelete(ProductVO productVO)throws Exception{
		System.out.println(productVO);
		int result = productService.setDelete(productVO);

		return "redirect:/product/list";
	}

	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(ProductVO productVO, MultipartFile [] files) throws Exception {
		System.out.println(files.length);
		
		for(MultipartFile f: files) {
			System.out.println(f.getOriginalFilename());
		}
		
		int result = productService.setInsert(productVO, files);
		
		return "redirect:/product/list";
	}
}
