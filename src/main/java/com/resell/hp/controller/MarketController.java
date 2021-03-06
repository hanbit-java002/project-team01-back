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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.FileService;
import com.resell.hp.service.MarketService;

@RestController
@RequestMapping("/api/market")
public class MarketController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketController.class);
	@Autowired
	private MarketService marketService;
	@Autowired
	private FileService fileService;

	
	@SignInRequired
	@RequestMapping(value="/selling", method = RequestMethod.POST)
	public Map productAdd(MultipartHttpServletRequest request, HttpSession session) {
		String productName = request.getParameter("name");
		String brandId = request.getParameter("brandId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String seriesId = request.getParameter("seriesId");
		int price = Integer.parseInt(request.getParameter("price"));
		String quality = request.getParameter("qualityId");
		String detail = request.getParameter("detail");
		List<MultipartFile> arrImgSrc = request.getFiles("arrImgSrc");
		int mainImgIndex = Integer.parseInt(request.getParameter("mainImgIndex"));
		String dealMeans = request.getParameter("dealMeans");
		String directPlace = request.getParameter("directPlace");
		boolean safeDeal = Boolean.parseBoolean(request.getParameter("safeDeal"));
		String loginId =(String) session.getAttribute("uid");
		String deliveryCheck= request.getParameter("deliveryCheck");
		
		System.out.println("멀티파트"+arrImgSrc);
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
	
	@SignInRequired
	@RequestMapping(value="/selling-update", method = RequestMethod.POST)
	public Map productUpdate(MultipartHttpServletRequest request) {
		String productId = request.getParameter("productId");
		String productName = request.getParameter("name");
		String brandId = request.getParameter("brandId");
		String categoryId = request.getParameter("categoryId");
		String sizeId = request.getParameter("sizeId");
		String seriesId = request.getParameter("seriesId");
		int price = Integer.parseInt(request.getParameter("price"));
		String quality = request.getParameter("qualityId");
		String detail = request.getParameter("detail");		
		String dealMeans = request.getParameter("dealMeans");
		String directPlace = request.getParameter("directPlace");
		boolean safeDeal = Boolean.parseBoolean(request.getParameter("safeDeal"));
		String deliveryCheck= request.getParameter("deliveryCheck");
		String status= request.getParameter("status");
		
		List<MultipartFile> arrImgSrc = request.getFiles("arrImgSrc");
		String[] arrDelImgId = request.getParameterValues("arrDelImgId");
		String mainImg = request.getParameter("mainImg");
		String beforeMainImg = request.getParameter("beforeMainImg");
		System.out.println(status);
		
		Map productInfo = new HashMap<String, Object>();
		productInfo.put("productId",productId);
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
		productInfo.put("deliveryCheck", deliveryCheck);
		productInfo.put("status", status);
		
		System.out.println(productInfo);
		
		Map productImgInfo = new HashMap<String, Object>();
		productImgInfo.put("productId",productId);
		productImgInfo.put("arrImgSrc", arrImgSrc);
		productImgInfo.put("arrDelImgId", arrDelImgId);
		productImgInfo.put("mainImg", mainImg);
		productImgInfo.put("beforeMainImg", beforeMainImg);
		
		System.out.println(productImgInfo);
		
		marketService.update(productInfo, productImgInfo);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
		
	}
	
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
	@SignInRequired
	@RequestMapping(value="/getProduct", method = RequestMethod.GET)
	public Map getProduct(@RequestParam("productId") String productId, HttpSession session) {
		System.out.println(productId);
		String sessionUid = (String) session.getAttribute("uid");
		System.out.println(sessionUid);
		Map productData = marketService.getProduct(productId);
		String sellerUid = (String) productData.get("seller_uid");
		
		Map result = new HashMap();
		if (sessionUid.equals(sellerUid)) {
			result.put("valid", "ok");
			result.put("product", productData);
			result.put("productImg", fileService.getProductImgs(productId));
			
		}
		else {
			result.put("valid", "no");
		}
		return result;
	}
	
	@SignInRequired
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public Map deleteProduct(@RequestParam("productId") String productId,
			@RequestParam("sellerUid") String sellerUid, HttpSession session) {
		Map result = new HashMap();
		String sessionUid= (String) session.getAttribute("uid");
		
		if (!sellerUid.equals(sessionUid)) {
			result.put("result", "no");
			return result;
		}
		
		marketService.deleteProduct(productId);
		result.put("result", "ok");
		
		return result;
	}
	
	@SignInRequired
	@RequestMapping(value="/sellingList", method = RequestMethod.GET)
	public Map getSellingList(@RequestParam("page") int page, 
			@RequestParam("rowsPerPage") int rowsPerPage, 
			@RequestParam("searchValue") String searchValue, HttpSession session) {
		String uid = (String) session.getAttribute("uid");		
		Map result = new HashMap();
		System.out.println(searchValue);
		
		int listCount = marketService.selectSellingCount(uid, searchValue);
		List list= marketService.selectSellingList(uid, page, rowsPerPage, searchValue);
		
		result.put("listCount", listCount);
		result.put("list", list);
	
		return result;
	}
	
	@SignInRequired
	@RequestMapping(value="/purchaseList", method = RequestMethod.GET)
	public Map getPurchaseList(@RequestParam("page") int page, 
			@RequestParam("rowsPerPage") int rowsPerPage, 
			@RequestParam("searchValue") String searchValue, HttpSession session) {
		String uid = (String) session.getAttribute("uid");		
		Map result = new HashMap();
		System.out.println(searchValue);
		
		int listCount = marketService.selectPurchaseCount(uid, searchValue);
		List list= marketService.selectPurchaseList(uid, page, rowsPerPage, searchValue);
		
		result.put("listCount", listCount);
		result.put("list", list);
	
		return result;
	}
	
	@SignInRequired
	@RequestMapping(value="/updateStatus", method = RequestMethod.GET)
	public Map updateStatus(@RequestParam("productId") String productId,
			@RequestParam("statusSelect") String statusSelect, 
			@RequestParam("statusSelectBefore") String statusSelectBefore) {
		Map result = new HashMap();
		System.out.println(productId+","+statusSelect+","+statusSelectBefore);
		marketService.updateStatus(productId, statusSelect, statusSelectBefore);
		result.put("result", "ok");
		
		return result;
	}
	

}
