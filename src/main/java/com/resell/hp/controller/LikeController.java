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
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.LikeService;
import com.resell.hp.service.MarketService;

@RestController
@RequestMapping("/api/like")
public class LikeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private MarketService marketService;
	
	//get list
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public Map productList(HttpServletRequest request) {
		String brandId = request.getParameter("brandId");
		String searchValue = request.getParameter("searchValue");
		String seriesId = request.getParameter("seriesId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String qualityId = request.getParameter("qualityId");
		String priceFilter = request.getParameter("priceFilter");
		int rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		Map filterInfo = new HashMap<String, Object>();
		filterInfo.put("brandId",brandId);
		filterInfo.put("searchValue",searchValue);
		filterInfo.put("categoryId",categoryId);
		filterInfo.put("seriesId",seriesId);
		filterInfo.put("sizeId",sizeId);
		filterInfo.put("qualityId",qualityId);  
		filterInfo.put("priceFilter",priceFilter);
		filterInfo.put("rowsPerPage", rowsPerPage);
		filterInfo.put("page", page);
		
		List list = marketService.selectProductList(filterInfo);
		int count = marketService.selectCount(filterInfo);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		return result;
	}
	
	// Like 추가
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
