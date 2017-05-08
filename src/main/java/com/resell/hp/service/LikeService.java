package com.resell.hp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.LikeDAO;

@Service
public class LikeService {
	
	@Autowired 
	private LikeDAO likeDAO;
	
	//Like 추가
	public int addLike(String productId, String uid) {
		return likeDAO.addLike(productId, uid);
	}
	
	//Like 삭제
	public int cancelLike(String productId, String uid) {
		return likeDAO.delete(productId, uid);
	}
}
