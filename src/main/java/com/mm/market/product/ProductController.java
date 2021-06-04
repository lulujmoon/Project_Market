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
	public String getList(Pager pager, Model model) throws Exception {
		
		List<ProductVO> ar = productService.getList(pager);
		List<CategoryVO> categories = categoryMapper.getList();
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		model.addAttribute("categories", categories);
		
		System.out.println(ar.get(0).getFiles().get(0).getFileName());
		
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
	public String setDelete(ProductVO productVO)throws Exception{
		System.out.println(productVO);
		int result = productService.setDelete(productVO);

		return "redirect:product/list";
	}

	
	@GetMapping("insert")
	public void setInsert() throws Exception {}
	
	@PostMapping("insert")
	public String setInsert(ProductVO productVO, MultipartFile [] file) throws Exception {
		
		int result = productService.setInsert(productVO, file);

		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String setUpdate(ProductVO productVO, Model model)throws Exception{
		productVO = productService.getSelect(productVO);
		
/*		List<ProductFileVO> file = productVO.getFiles();
		
		for(int i=0;i<file.size();i++) {
			file.get(i).setProductNum(productVO.getProductNum());
		}
		
		model.addAttribute("files", file);
		System.out.println(file);
*/		
		
		model.addAttribute("vo", productVO);
		
		return "product/update";
	}
	
	@PostMapping("update")
	public String setUpdate(ProductVO productVO, MultipartFile file)throws Exception{
		productService.setUpdate(productVO, file);
		productVO = productService.getSelect(productVO);
				
		return "redirect:./list";
	}
}
