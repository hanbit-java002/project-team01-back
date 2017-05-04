package com.resell.hp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//product 리스트 불러오기
	@RequestMapping("/list") 
	public Map getList(@RequestParam("currentPage") int currentPage,
			@RequestParam("rowsPerPage") int rowsPerPage,
			@RequestParam("brandName") String brandName) {
		
		List list = productService.getList(currentPage, rowsPerPage, brandName);
		int count = productService.countList(brandName);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		
		return result;
	}
	
	//product detail 정보 가져오기
	@RequestMapping(value="/detail/{productId}", method=RequestMethod.GET)
	public List getProductDetail(@PathVariable("productId") String productId) {
		return productService.getProductDetail(productId);
	}
		
}
