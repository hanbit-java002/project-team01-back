package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.HitsDAO;
import com.resell.hp.dao.MarketDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class MarketService {
	@Autowired
	private MarketDAO marketDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private HitsDAO hitsDAO;
	
	@Transactional 
	public void add(Map productInfo, Map productImgInfo) {
	
		String productId= KeyUtils.generateKey("PRO");
		
		productInfo.put("productId", productId);
		productImgInfo.put("productId", productId);
		
		marketDAO.insert(productInfo);
		fileService.addAndSaveProductImg(productImgInfo);
		hitsDAO.addHits(productId);
		
	}
	
	@Transactional
	public List<Map<String,Object>> selectProductList(Map filterInfo){
		
		return marketDAO.selectProductList(filterInfo);
	}
	
}
