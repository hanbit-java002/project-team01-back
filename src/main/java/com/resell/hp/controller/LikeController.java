package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.LikeService;
import com.resell.hp.service.ProductService;

@RestController
@RequestMapping("/api/like")
public class LikeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private LikeService likeService;
	
	// Like 추가
	@RequestMapping(value="add/{productId}", method=RequestMethod.GET)
	public Map addLike(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		likeService.addLike(productId, uid);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	//Like 삭제
	@RequestMapping(value="cancel/{productId}", method=RequestMethod.GET)
	public Map cancelLike(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		LOGGER.info(uid);
		likeService.cancelLike(productId, uid);
		
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
