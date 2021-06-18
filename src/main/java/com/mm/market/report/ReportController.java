package com.mm.market.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ReportController {
	
	@Autowired
    private final ReportService reportService;
	
	@Autowired
	private ProductService productService;

    @GetMapping("/report/report")
    public String dispMail(ProductVO productVO, Model model) {
			try {
				productVO = productService.getSelect(productVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("product", productVO);
		
        return "/report/report";
    }

    @PostMapping("/report")
    public String execMail(ReportVO reportVO) {
        reportService.mailSend(reportVO);
        
        return "redirect:/product/list";
    }
}

