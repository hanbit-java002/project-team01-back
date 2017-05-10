package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.HitsService;

@RestController
@RequestMapping("/api/hits")
public class HitsController {

	@Autowired
	private HitsService hitsService;
	
	// count 조회수
	@RequestMapping(value="/count/{productId}", method=RequestMethod.GET)
	public int countHits(@PathVariable("productId") String productId) {
		return hitsService.countHits(productId);
	}
	
	//조회수 추가
	@RequestMapping(value="/plus/{productId}", method=RequestMethod.GET)
	public Map plusHits(@PathVariable("productId") String productId) {
		hitsService.plusHits(productId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
