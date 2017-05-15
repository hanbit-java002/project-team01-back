package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.LikeDAO;
import com.resell.hp.dao.MarketDAO;

@Service
public class LikeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LikeService.class);
	
	@Autowired 
	private LikeDAO likeDAO;
	
	@Autowired 
	private MarketDAO marketDAO;
	
	public List<Map<String,Object>> selectProductList(Map filterInfo){
		
		return marketDAO.selectProductList(filterInfo);
	}

	public int selectCount(Map filterInfo) {
		return marketDAO.selectCount(filterInfo);
	}
	
	//Like 추가
	@Transactional
	public int addLike(String productId, String uid) {
		return likeDAO.addLike(productId, uid);
	}
	
	//Like 삭제
	@Transactional
	public int cancelLike(String productId, String uid) {
		return likeDAO.delete(productId, uid);
	}
	
	//like count
	public int countLike(String productId) {
		return likeDAO.countLike(productId);
	}

	public boolean hasLike(String productId, String uid) {
		System.out.println(productId + ", " + uid);
		
		if (likeDAO.hasLike(productId, uid) > 0) {
			return true;
		}
		
		return false;
	}
	
}
