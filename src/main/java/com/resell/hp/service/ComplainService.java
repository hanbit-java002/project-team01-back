package com.resell.hp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.ComplainDAO;

@Service
public class ComplainService {
	
	@Autowired 
	private ComplainDAO complainDAO;
	
	//complain 추가
	@Transactional
	public int addComplain(String productId, String uid) {
		return complainDAO.addComplain(productId, uid);
	}
	
	//has complain
	public boolean hasComplain(String productId, String uid) {
		
		if (complainDAO.hasComplain(productId, uid) > 0) {
			return true;
		}
		
		return false;
	}
	
}
