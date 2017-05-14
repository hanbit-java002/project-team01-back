package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.CommentDAO;
import com.resell.hp.dao.ComplainDAO;
import com.resell.hp.dao.DealDAO;
import com.resell.hp.dao.FileDAO;
import com.resell.hp.dao.HitsDAO;
import com.resell.hp.dao.LikeDAO;
import com.resell.hp.dao.MarketDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class MarketService {
	@Autowired
	private MarketDAO marketDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private FileDAO fileDAO;
	@Autowired
	private HitsDAO hitsDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private DealDAO dealDAO;
	@Autowired
	private ComplainDAO complainDAO;
	@Autowired
	private LikeDAO likeDAO;
	
	
	@Transactional 
	public void add(Map productInfo, Map productImgInfo) {
	
		String productId= KeyUtils.generateKey("PRO");
		
		productInfo.put("productId", productId);
		productImgInfo.put("productId", productId);
		
		marketDAO.insert(productInfo);
		fileService.addAndSaveProductImg(productImgInfo);
		hitsDAO.initHits(productId);
	}
	
	public List<Map<String,Object>> selectProductList(Map filterInfo){
		
		return marketDAO.selectProductList(filterInfo);
	}

	public int selectCount(Map filterInfo) {
		return marketDAO.selectCount(filterInfo);
	}

	public Map getProduct(String productId) {
		return marketDAO.selectProduct(productId);
	}
	
	@Transactional
	public void update(Map productInfo, Map productImgInfo) {
		marketDAO.update(productInfo);
		fileService.update(productImgInfo);		
	}
	
	@Transactional
	public void deleteProduct(String productId) {
		hitsDAO.deleteProduct(productId);
		commentDAO.deleteProduct(productId);
		dealDAO.deleteProduct(productId);
		complainDAO.deleteProduct(productId);
		likeDAO.deleteProduct(productId);
		fileService.deleteProduct(productId);
		marketDAO.deleteProduct(productId);
	}

	public List selectSellingList(String uid, int page, int rowsPerPage, String searchValue) {
		return marketDAO.selectSellingList(uid, page, rowsPerPage, searchValue);
	}

	public int selectSellingCount(String uid, String searchValue) {
		return marketDAO.selectSellingCount(uid, searchValue);
	}
}
