package com.resell.hp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HitsDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//product & Hits 초기화
	public int initHits(String productId) {		
		return sqlSession.insert("hits.initHits", productId);
	}
	
	// count 조회수
	public int countHits(String productId) {		
		return sqlSession.selectOne("hits.countHits", productId);
	}
	
	//조회수 추가
	public int plusHits(String productId) {
		return sqlSession.insert("hits.plusHits", productId);
	}

	public void deleteProduct(String productId) {
		sqlSession.delete("hits.deleteProduct", productId);
	}
}
