package com.resell.hp.service;

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

}
