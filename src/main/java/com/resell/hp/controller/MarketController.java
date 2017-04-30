package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/market")
public class MarketController {
	
	@RequestMapping(value="/selling", method = RequestMethod.POST)
	public Map productAdd(MultipartHttpServletRequest request) {
		
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}

}
