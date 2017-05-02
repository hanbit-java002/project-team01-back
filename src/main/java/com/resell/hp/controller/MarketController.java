package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.MarketService;

@RestController
@RequestMapping("/api/market")
public class MarketController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketController.class);
	
	private MarketService marketService;
	
	@SignInRequired
	@RequestMapping(value="/selling", method = RequestMethod.POST)
	public Map productAdd(HttpServletRequest request, HttpSession session) {
		String productName = request.getParameter("name");
		String brandId = request.getParameter("brandId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String seriesId = request.getParameter("seriesId");
		String price = request.getParameter("price");
		String quality = request.getParameter("qualityId");
		String detail = request.getParameter("detail");
		String[] arrImgSrc = request.getParameterValues("arrImgSrc");
		int mainImgIndex = Integer.parseInt(request.getParameter("mainImgIndex"));
		String dealMeans = request.getParameter("dealMeans");
		String dealPlace = request.getParameter("dealPlace");
		boolean safeDeal = Boolean.parseBoolean(request.getParameter("safeDeal"));
		
		Map productInfo = new HashMap<String, Object>();
		productInfo.put("productName",productName);
		productInfo.put("brandId",brandId);
		productInfo.put("categoryId",categoryId);
		productInfo.put("sizeId",sizeId);
		productInfo.put("seriesId",seriesId);
		productInfo.put("price",price);
		productInfo.put("quality",quality);
		productInfo.put("detail",detail);
		productInfo.put("arrImgSrc",arrImgSrc);
		productInfo.put("mainImgIndex",mainImgIndex);
		productInfo.put("dealMeans",dealMeans);
		productInfo.put("dealPlace",dealPlace);
		productInfo.put("safeDeal",safeDeal);
		
		
		System.out.println("배열 데이터"+arrImgSrc.length);
		
		if (arrImgSrc != null) {
			for (int i=0; i<arrImgSrc.length; i++) {
				String dataUrl = arrImgSrc[i];
				
				String contentType = StringUtils.substringBetween("data:", ";base64,");
				String base64 = StringUtils.substringAfter(dataUrl, ";base64,");
				
				byte[] bytes = Base64.decodeBase64(base64);
			
				
			}
		}
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}

}
