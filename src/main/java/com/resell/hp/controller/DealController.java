package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.DealService;

@RestController
@RequestMapping("/api/deal")
public class DealController {

	@Autowired
	private DealService dealService;
	
	// 구매
	@SignInRequired
	@RequestMapping(value="/purchase/{productId}", method=RequestMethod.GET)
	public Map purchase(HttpSession session, @PathVariable("productId") String productId,
			@RequestParam("userName") String userName,
			@RequestParam("phoneNum") String phoneNum,
			@RequestParam("dealMeans") String dealMeans,
			@RequestParam("safeDeal") String safeDeal,
			@RequestParam("directPlace") String directPlace,
			@RequestParam("zipcode") String zipcode,
			@RequestParam("address") String address) {
		
		String purchaserUid = (String) session.getAttribute("uid");
		
		dealService.purchase(productId, userName, phoneNum, dealMeans, safeDeal, directPlace, zipcode, address, purchaserUid);
		dealService.updateStatus(productId);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	
	// 구매자 정보 가져오기
	@SignInRequired
	@RequestMapping(value="/purchaser/{productId}", method=RequestMethod.GET)
	public Map getPurchaserInfo(HttpSession session, @PathVariable("productId") String productId) {
		
		String purchaserUid = (String) session.getAttribute("uid");
		return dealService.getPurchaserInfo(productId, purchaserUid);
	}
}
