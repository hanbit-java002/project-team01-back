package com.resell.hp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	//brand Id 가져오기
	@RequestMapping("/brand") 
	public List getBrandId() {
		return productService.getBrandId();
	}
	
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
	public Map getProductDetail(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		Map productInfo = productService.getProductDetail(productId);
		try {
			Map result = new HashMap();
			result.put("productInfo", productInfo);
			result.put("sessionUid", uid);
			System.out.println("result : " + result);
			return result;
			
			
		} catch (Exception e) {
			Map result = new HashMap();
			result.put("productInfo", productInfo);
			result.put("sessionUid", "null");
			return result;
		}
	}
	
	//product image 정보 가져오기
	@RequestMapping(value="/image/{productId}", method=RequestMethod.GET)
	public List getProductImage(@PathVariable("productId") String productId) {
		return productService.getProductImage(productId);
	}	

	//판매자 정보 가져오기
	@RequestMapping(value="/seller/{productId}", method=RequestMethod.GET)
	public Map getSellerInfo(@PathVariable("productId") String productId) {
	
		Map sellerInfo = productService.getSellerInfo(productId);
		int countSell = productService.countSell(productId);
		
		Map result = new HashMap();
		result.put("sellerInfo", sellerInfo);
		result.put("countSell", countSell);
		
		return result;
	}
	
	//comment count
	@RequestMapping(value="/comment/{productId}", method=RequestMethod.GET)
	public int countComment(@PathVariable("productId") String productId) {
		return productService.countComment(productId);
	}
	
	//complain count
	@RequestMapping(value="/complain/{productId}", method=RequestMethod.GET)
	public int countComplain(@PathVariable("productId") String productId) {
		return productService.countComplain(productId);
	}
}
