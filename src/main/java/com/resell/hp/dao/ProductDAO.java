package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List selectList(int currentPage, int rowsPerPage, String brandId) {
		Map param = new HashMap();
		param.put("firstIndex", (currentPage - 1) * rowsPerPage);
		param.put("rowsPerPage", rowsPerPage);
		param.put("brandId", brandId);

		return sqlSession.selectList("product.selectList", param);
	}
	
	// 다이나믹 SQL을 사용할 때 변수는 항상 map으로 넘겨줘야 에러가 안 난다! 	
	public int countList(String brandId) {
		Map param = new HashMap();
		param.put("brandId", brandId);
		
		return sqlSession.selectOne("admin.adminMember.countList", param);
	}
}
