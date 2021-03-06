package com.resell.hp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.DealDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class DealService {
	
	@Autowired
	private DealDAO dealDAO;
	
	// 구매
	@Transactional
	public int purchase(String productId, String userName, String phoneNum, String dealMeans, String safeDeal,
			String directPlace, String zipcode, String address, String purchaserUid) {
		
		String dealId = KeyUtils.generateKey("DEA");
		return dealDAO.addDeal(dealId, productId, userName, phoneNum, dealMeans, 
				safeDeal, directPlace, zipcode, address, purchaserUid);
	}
	
	@Transactional
	public int updateStatus(String productId) {
		
		return dealDAO.updateStatus(productId);
	}
	
	// 구매자 정보 가져오기
	@Transactional
	public Map getPurchaserInfo(String productId, String purchaserUid) {
		
		return dealDAO.selectPurchaserInfo(productId, purchaserUid);
	}

	// 구매자 정보 가져오기
	@Transactional
	public Map getUserInfo(String purchaserUid) {
		
		return dealDAO.selectUserInfo(purchaserUid);
	}

	public Map getsellingPurchaser(String productId) {
		return dealDAO.getsellingPurchaser(productId);
	}
}
