package com.resell.hp.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.MarketDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class MarketService {
	@Autowired
	private MarketDAO marketDAO;
	@Autowired
	private FileService fileService;
	
	@Transactional
	public void add(Map productInfo, Map productImgInfo) {
	
		String productId= KeyUtils.generateKey("PRO");
		
		productInfo.put("productId", productId);
		productImgInfo.put("productId", productId);
		
		marketDAO.insert(productInfo);
		
		fileService.addAndSaveProductImg(productImgInfo);
		
	}
	
}
