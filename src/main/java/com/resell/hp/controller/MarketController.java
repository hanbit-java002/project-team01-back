package com.resell.hp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.MarketService;

@RestController
@RequestMapping("/api/market")
public class MarketController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketController.class);
	@Autowired
	private MarketService marketService;
	
	@SignInRequired
	@RequestMapping(value="/selling", method = RequestMethod.POST)
	public Map productAdd(HttpServletRequest request, HttpSession session) {
		String productName = request.getParameter("name");
		String brandId = request.getParameter("brandId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String seriesId = request.getParameter("seriesId");
		int price = Integer.parseInt(request.getParameter("price"));
		String quality = request.getParameter("qualityId");
		String detail = request.getParameter("detail");
		String[] arrImgSrc = request.getParameterValues("arrImgSrc");
		int mainImgIndex = Integer.parseInt(request.getParameter("mainImgIndex"));
		String dealMeans = request.getParameter("dealMeans");
		String directPlace = request.getParameter("dealPlace");
		boolean safeDeal = Boolean.parseBoolean(request.getParameter("safeDeal"));
		String loginId =(String) session.getAttribute("uid");
		String deliveryCheck= request.getParameter("deliveryCheck");
		
		Map productInfo = new HashMap<String, Object>();
		productInfo.put("productName",productName);
		productInfo.put("brandId",brandId);
		productInfo.put("categoryId",categoryId);
		productInfo.put("sizeId",sizeId);
		productInfo.put("seriesId",seriesId);  
		productInfo.put("price",price);
		productInfo.put("quality",quality);
		productInfo.put("detail",detail);
		productInfo.put("dealMeans",dealMeans);
		productInfo.put("directPlace",directPlace);
		productInfo.put("safeDeal",safeDeal);
		productInfo.put("loginId",loginId);
		productInfo.put("status","selling");
		productInfo.put("deliveryCheck", deliveryCheck);
		
		Map productImgInfo = new HashMap<String, Object>();
		productImgInfo.put("arrImgSrc",arrImgSrc);
		productImgInfo.put("mainImgIndex",mainImgIndex);
		
		marketService.add(productInfo, productImgInfo);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public List<Map<String,Object>> productList(HttpServletRequest request, HttpSession session) {

		
		
		String brandId = request.getParameter("brandId");
		String searchValue = request.getParameter("searchValue");
		String seriesId = request.getParameter("seriesId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String qualityId = request.getParameter("qualityId");
		String priceFilter = request.getParameter("priceFilter");
		
		Map filterInfo = new HashMap<String, Object>();
		filterInfo.put("brandId",brandId);
		filterInfo.put("searchValue",searchValue);
		filterInfo.put("seriesId",seriesId);
		filterInfo.put("sizeId",sizeId);
		filterInfo.put("qualityId",qualityId);  
		filterInfo.put("priceFilter",priceFilter);
		
		return marketService.selectProductList(filterInfo);
	}

}
