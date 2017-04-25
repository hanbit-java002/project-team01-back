package com.resell.hp.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList() {
		return sqlSession.selectList("admin.brand.selectList");
	}
	
	public Map selectOne(String brandId) {
		return sqlSession.selectOne("admin.brand.selectOne", brandId);
	}
	
	public int update(String brandId, String brandName) {
		Map param = new HashMap();
		param.put("brandId", brandId);
		param.put("brandName", brandName);
		
		return sqlSession.insert("admin.brand.update", param);
	}
	
	public int delete(String brandId) {
		return sqlSession.insert("admin.brand.delete", brandId);
	}
	
	public int insert(String brandId, String brandName) {
		Map param = new HashMap();
		param.put("brandId", brandId);
		param.put("brandName", brandName);
		
		return sqlSession.insert("admin.brand.insert", param);
	}
}
