package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//Like 추가
	public int addLike(String productId, String uid) {		
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		
		return sqlSession.insert("like.addLike", param);
	}
	
	//Like 삭제
	public int delete(String productId, String uid) {
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		
		return sqlSession.delete("like.delete", param);
	}
	
	//like count
	public int countLike(String productId) {
		return sqlSession.selectOne("like.countLike", productId);
	}

	public int hasLike(String productId, String uid) {
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		
		return sqlSession.selectOne("like.hasLike", param);
	}

	public void deleteProduct(String productId) {
		sqlSession.delete("like.deleteProduct", productId);
	}
}
