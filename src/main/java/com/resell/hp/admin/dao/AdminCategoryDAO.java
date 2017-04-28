package com.resell.hp.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminCategoryDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList(int currentPage, int rowsPerPage) {
		Map param = new HashMap();
		param.put("firstIndex", (currentPage - 1) * rowsPerPage);
		param.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("admin.adminCategory.selectList", param);
	}
	
	public int countList() {
		return sqlSession.selectOne("admin.adminCategory.countList");
	}
	
	public Map selectOne(String categoryId) {
		return sqlSession.selectOne("admin.adminCategory.selectOne", categoryId);
	}
	
	public int update(String categoryId, String categoryName) {
		Map param = new HashMap();
		param.put("categoryId", categoryId);
		param.put("categoryName", categoryName);
		
		return sqlSession.insert("admin.adminCategory.update", param);
	}
	
	public int delete(String categoryId) {
		return sqlSession.insert("admin.adminCategory.delete", categoryId);
	}
	
	public int insert(String categoryId, String categoryName) {
		Map param = new HashMap();
		param.put("categoryId", categoryId);
		param.put("categoryName", categoryName);
		
		return sqlSession.insert("admin.adminCategory.insert", param);
	}
}
