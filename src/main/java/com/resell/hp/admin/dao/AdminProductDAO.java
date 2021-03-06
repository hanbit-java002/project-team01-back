package com.resell.hp.admin.dao;

import java.util.HashMap;
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
	
	public int selectStatusCount(Map filterInfo) {
		return sqlSession.selectOne("admin.adminProduct.selectStatusCount",filterInfo);
	}

	
	//selling_status 변경
	public int updateStatus(String menuCategory, String productId) {
		Map param = new HashMap();
		param.put("menuCategory", menuCategory);
		param.put("productId", productId);
		
		return sqlSession.insert("admin.adminProduct.updateStatus", param);
	}
	
	//검색 리스트
	public List<Map<String, Object>> selectSearchList(Map filterInfo) {
		int page = (int) filterInfo.get("page");
		int rowsPerPage = (int) filterInfo.get("rowsPerPage");
		System.out.println("page"+page);
		filterInfo.put("firstIndex", (page) * rowsPerPage);
		filterInfo.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("admin.adminProduct.selectSearchList",filterInfo);		
	}
	public int selectSearchCount(String menuCategory, String searchValue) {
		Map sellinginfo = new HashMap();
		sellinginfo.put("menuCategory", menuCategory);
		sellinginfo.put("searchValue", searchValue);
		return sqlSession.selectOne("admin.adminProduct.selectSearchCount", sellinginfo);
	}
}
