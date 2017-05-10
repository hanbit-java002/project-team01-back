package com.resell.hp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.LikeDAO;

@Service
public class LikeService {
	
	@Autowired 
	private LikeDAO likeDAO;
	
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
		if (likeDAO.hasLike(productId, uid) > 0) {
			return true;
		}
		
		return false;
	}
	
}
