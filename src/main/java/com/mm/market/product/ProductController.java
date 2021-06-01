package com.mm.market.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.Pager;

@Controller
@RequestMapping("/product/**")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception {
		
		List<ProductVO> ar = productService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		return "product/list";
	}
	
	@GetMapping("select")
	public String getSelect(ProductVO productVO, Model model)throws Exception {
		productVO = productService.getSelect(productVO);
		System.out.println(productVO);
		
		model.addAttribute("vo", productVO);
		return "product/select";
	}
	
	@GetMapping("delete")
	public String setDelete(ProductVO productVO)throws Exception{
		System.out.println(productVO);
		int result = productService.setDelete(productVO);

		return "redirect:product/list";
	}

	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(ProductVO productVO, MultipartFile [] files) throws Exception {
		  //Test
//		  System.out.println(files.length); 
//		  
//		  for(MultipartFile f : files) {
//		  		System.out.println(f.getOriginalFilename()); 
//		  }
		 
		
		int result = productService.setInsert(productVO, files);
		
		return "redirect:product/list";
	}
	
	@GetMapping("update")
	public String setUpdate(ProductVO productVO, Model model)throws Exception{
		productVO = productService.getSelect(productVO);
		model.addAttribute("vo", productVO);
		
		return "product/update";
		
	}
	
	@PostMapping("update")
	public String setUpdate(ProductVO productVO)throws Exception{
		int result = productService.setUpdate(productVO);
		
		
		
		return "redirect:./list";
	}
}
