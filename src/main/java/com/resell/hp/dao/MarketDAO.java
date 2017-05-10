package com.resell.hp.dao;

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
		System.out.println(filterInfo);
		
		return sqlSession.selectList("market.selectList",filterInfo);
/*		if ((filterInfo.get("brandId") == null || "brand-all".equals((filterInfo.get("brandId"))))
				&& (filterInfo.get("searchValue") == null || "".equals((filterInfo.get("searchValue"))))
				&& (filterInfo.get("seriesId") == null || "series-all".equals((filterInfo.get("seriesId"))))
				&& (filterInfo.get("categoryId") == null || "cateory-all".equals((filterInfo.get("categoryId"))))
				&& (filterInfo.get("sizeId") == null || "size-all".equals((filterInfo.get("sizeId"))))
				&& (filterInfo.get("qualityId") == null || "quality-all".equals((filterInfo.get("qualityId"))))				
				) {
			return sqlSession.selectList("market.selectList",filterInfo);
		}
		else {
			return sqlSession.selectList("market.selectFilterList",filterInfo);
		}*/
		
		
	}

}
