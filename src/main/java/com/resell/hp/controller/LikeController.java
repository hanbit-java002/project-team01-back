package com.resell.hp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.LikeService;
import com.resell.hp.service.MarketService;

@RestController
@RequestMapping("/api/like")
public class LikeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private LikeService likeService;
	
	//get like list
	@SignInRequired
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public Map productList(HttpSession session,
			@RequestParam("rowsPerPage") int rowsPerPage,
			@RequestParam("page") int page,
			@RequestParam("searchValue") String searchValue) {
		
		String uid = (String) session.getAttribute("uid");
		
		List list = likeService.selectLikeList(uid, rowsPerPage, page, searchValue);
		int count = likeService.selectCount(uid, searchValue);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		return result;
	}
	
	// Like 추가
	@SignInRequired
	@RequestMapping(value="/add/{productId}", method=RequestMethod.GET)
	public Map addLike(HttpSession session, @PathVariable("productId") String productId) {
		try {
			String uid = (String) session.getAttribute("uid");
			likeService.addLike(productId, uid);
			
			Map result = new HashMap();
			result.put("result", "ok");
			return result;
			
		} catch (Exception e) {
			Map result = new HashMap();
			result.put("result", "no");
			return result;
		}
		
	}
	
	//Like 삭제
	@SignInRequired
	@RequestMapping(value="/cancel/{productId}", method=RequestMethod.GET)
	public Map cancelLike(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		likeService.cancelLike(productId, uid);
		
		Map result = new HashMap();
		result.put("result", "ok");
	
		return result;
	}
	
	//like count
	@RequestMapping(value="/count/{productId}", method=RequestMethod.GET)
	public int countLike(@PathVariable("productId") String productId) {
		return likeService.countLike(productId);
	}
	
	//has like
	@RequestMapping(value="/hasLike/{productId}", method=RequestMethod.GET)
	public Map hasLike(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		boolean hasLike = likeService.hasLike(productId, uid);
		
		Map result = new HashMap();
		result.put("result", hasLike);
		
		return result;
	}
	
}
