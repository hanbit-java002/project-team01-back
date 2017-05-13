package com.resell.hp.dao;

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

}
