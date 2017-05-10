package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminBrandDAO;
import com.resell.hp.dao.ProductDAO;

@Service
public class ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired 
	private ProductDAO productDAO;
	
	@Autowired
	private AdminBrandDAO adminBrandDAO;
	
	//brand Id 가져오기
	public List getBrandId() {
		return productDAO.selectBrandId();
	}
	
	//product 리스트 불러오기
	public List getList(int currentPage, int rowsPerPage, String brandName) {
		String brandId = adminBrandDAO.selectBrandId(brandName);
		LOGGER.info(brandId);
		return productDAO.selectList(currentPage, rowsPerPage, brandId);
	}
	
	public int countList(String brandName) {
		String brandId = adminBrandDAO.selectBrandId(brandName);
		return productDAO.countList(brandId);
	}
	
	//product detail 정보 가져오기
	public List getProductDetail(String productId) {
		return productDAO.selectProductDetail(productId);
	}
	
	//product image 정보 가져오기
	public List getProductImage(String productId) {
		return productDAO.selectProductImage(productId);
	}
	
	
	//판매자 정보 가져오기
	public Map getSellerInfo(String productId) {
		return productDAO.selectSellerInfo(productId);
	}
	
	//count Sell 가져오기
	public int countSell(String productId) {
		return productDAO.countSell(productId);
	}

	
	//comment count
	public int countComment(String productId) {
		return productDAO.countComment(productId);
	}
	
	//complain count
	public int countComplain(String productId) {
		return productDAO.countComplain(productId);
	}
}
