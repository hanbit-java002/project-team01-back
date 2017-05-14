package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDAO {
	@Autowired
	private SqlSession sqlSession;  
	
	public int insert(Map productInfo) {
		return sqlSession.insert("market.insert", productInfo);
	}
	
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

	public Map selectProduct(String productId) {
		System.out.println(productId);
		return sqlSession.selectOne("market.selectProduct",productId);
	}

	public void update(Map productInfo) {
		System.out.println(productInfo);
		sqlSession.update("market.update",productInfo);
	}

	public void deleteProduct(String productId) {
		System.out.println("프로덕트: "+ productId);
		sqlSession.delete("market.deleteProduct", productId);
	}

	public List selectSellingList(String uid, int page, int rowsPerPage, String searchValue) {
		Map sellinginfo = new HashMap();
		sellinginfo.put("uid", uid);
		sellinginfo.put("firstIndex", (page)*rowsPerPage);
		sellinginfo.put("rowsPerPage", rowsPerPage);
		sellinginfo.put("searchValue", searchValue);
		return sqlSession.selectList("market.selectSellingList", sellinginfo);
	}

	public int selectSellingCount(String uid, String searchValue) {
		Map sellinginfo = new HashMap();
		sellinginfo.put("uid", uid);
		sellinginfo.put("searchValue", searchValue);
		return sqlSession.selectOne("market.selectSellingCount", sellinginfo);
	}


}
