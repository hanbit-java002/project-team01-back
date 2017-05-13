package com.resell.hp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealDAO {
	@Autowired
	private SqlSession sqlSession;

	public void deleteProduct(String productId) {
		System.out.println("딜: "+ productId);
		sqlSession.delete("deal.deleteProduct", productId);		
	}
	
	// 구매
	public int addDeal(String dealId, String productId, String userName, String phoneNum, String dealMeans, String safeDeal,
			String directPlace, String zipcode, String address, String purchaserUid) {
		
		Map param = new HashMap();
		param.put("dealId", dealId);
		param.put("productId", productId);
		param.put("userName", userName);
		param.put("phoneNum", phoneNum);
		param.put("dealMeans", dealMeans);
		param.put("safeDeal", safeDeal);
		param.put("directPlace", directPlace);
		param.put("zipcode", zipcode);
		param.put("address", address);
		param.put("purchaserUid", purchaserUid);
		
		return sqlSession.insert("deal.addDeal", param);
	}

}
