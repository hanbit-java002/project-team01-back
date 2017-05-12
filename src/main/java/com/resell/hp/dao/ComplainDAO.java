package com.resell.hp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComplainDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//complain 추가
	public int addComplain(String productId, String uid) {		
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		
		return sqlSession.insert("complain.addComplain", param);
	}
	
	//has complain
	public int hasComplain(String productId, String uid) {
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		
		return sqlSession.selectOne("complain.hasComplain", param);
	}

	public void deleteProduct(String productId) {
		sqlSession.delete("complain.deleteProduct", productId);			
	}
}
