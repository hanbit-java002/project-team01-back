package com.resell.hp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.HitsDAO;

@Service
public class HitsService {
	
	@Autowired 
	private HitsDAO hitsDAO;
	
	//product & Hits 추가
	public int addHits(String productId) {
		return hitsDAO.addHits(productId);
	}
	
	// count 조회수
	public int countHits(String productId) {
		return hitsDAO.countHits(productId);
	}
	
	//조회수 추가
	public int plusHits(String productId) {
		return hitsDAO.plusHits(productId);
	}
}
