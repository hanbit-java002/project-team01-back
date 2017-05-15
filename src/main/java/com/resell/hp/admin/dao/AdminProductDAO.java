package com.resell.hp.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminProductDAO {

	@Autowired
	private SqlSession sqlSession;  
	
	public List<Map<String, Object>> selectProductList(Map filterInfo) {
		int page = (int) filterInfo.get("page");
		int rowsPerPage = (int) filterInfo.get("rowsPerPage");
		System.out.println("page"+page);
		filterInfo.put("firstIndex", (page) * rowsPerPage);
		filterInfo.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("admin.adminProduct.selectList",filterInfo);		
	}

	public int selectCount(Map filterInfo) {
		return sqlSession.selectOne("admin.adminProduct.selectCount",filterInfo);
	}
}