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
	
	//brand Id 가져오기
	public List selectBrandId() {		
		return sqlSession.selectList("product.selectBrandId");
	}
	
	//product 리스트 불러오기
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
	
	//product detail 정보 가져오기
	public List selectProductDetail(String productId) {
		return sqlSession.selectList("product.selectProductDetail", productId);
	}
	
	//product image 정보 가져오기
	public List selectProductImage(String productId) {
		return sqlSession.selectList("product.selectProductImage", productId);
	}
	
	//판매자 정보 가져오기	
	public Map selectSellerInfo(String productId) {
		return sqlSession.selectOne("product.selectSellerInfo", productId);
	}
	
	//count Sell 가져오기
	public int countSell(String productId) {
		return sqlSession.selectOne("product.countSell", productId);
	}
	
	//like count
	public int countLike(String productId) {
		return sqlSession.selectOne("product.countLike", productId);
	}
	
	//comment count
	public int countComment(String productId) {
		return sqlSession.selectOne("product.countComment", productId);
	}
	
	//complain count
	public int countComplain(String productId) {
		return sqlSession.selectOne("product.countComplain", productId);
	}
	
}
