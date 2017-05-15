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
	
	public List<Map<String, Object>> selectProductList(Map filterInfo) {
		int page = (int) filterInfo.get("page");
		int rowsPerPage = (int) filterInfo.get("rowsPerPage");
		System.out.println("page"+page);
		filterInfo.put("firstIndex", (page) * rowsPerPage);
		filterInfo.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("market.selectList",filterInfo);		
	}

	public int selectCount(Map filterInfo) {
		return sqlSession.selectOne("market.selectCount",filterInfo);
	}
	
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
		System.out.println("라이크: "+ productId);
		sqlSession.delete("like.deleteProduct", productId);
	}
}
