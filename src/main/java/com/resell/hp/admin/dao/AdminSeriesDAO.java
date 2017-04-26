package com.resell.hp.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSeriesDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList() {
		return sqlSession.selectList("admin.adminSeries.selectList");
	}
	
	public Map selectOne(String seriesId) {
		return sqlSession.selectOne("admin.adminSeries.selectOne", seriesId);
	}
	
	public int update(String seriesId, String seriesName) {
		Map param = new HashMap();
		param.put("seriesId", seriesId);
		param.put("seriesName", seriesName);
		
		return sqlSession.insert("admin.adminSeries.update", param);
	}
	
	public int delete(String seriesId) {
		return sqlSession.insert("admin.adminSeries.delete", seriesId);
	}
	
	public int insert(String seriesId, String seriesName, String brandId) {
		Map param = new HashMap();
		param.put("seriesId", seriesId);
		param.put("seriesName", seriesName);
		param.put("brandId", brandId);
		
		return sqlSession.insert("admin.adminSeries.insert", param);
	}
}
