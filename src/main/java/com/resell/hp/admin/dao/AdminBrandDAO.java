package com.resell.hp.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminBrandDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList(int currentPage, int rowsPerPage) {
		Map param = new HashMap();
		param.put("firstIndex", (currentPage - 1) * rowsPerPage);
		param.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("admin.adminBrand.selectList", param);
	}
	
	public int countList() {
		return sqlSession.selectOne("admin.adminCategory.countList");
	}
	
	public Map selectOne(String brandId) {
		return sqlSession.selectOne("admin.adminBrand.selectOne", brandId);
	}
	
	public String selectBrandId(String brandName) {
		return sqlSession.selectOne("admin.adminBrand.selectBrandId", brandName); 
	}
	
	public int update(String brandId, String brandName) {
		Map param = new HashMap();
		param.put("brandId", brandId);
		param.put("brandName", brandName);
		
		return sqlSession.insert("admin.adminBrand.update", param);
	}
	
	public int delete(String brandId) {
		return sqlSession.insert("admin.adminBrand.delete", brandId);
	}
	
	public int insert(String brandId, String brandName) {
		Map param = new HashMap();
		param.put("brandId", brandId);
		param.put("brandName", brandName);
		
		return sqlSession.insert("admin.adminBrand.insert", param);
	}
}
