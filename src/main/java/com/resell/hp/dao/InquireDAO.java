package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InquireDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//get inquire list
	public List selectInquireList(String uid, int rowsPerPage, int page, String searchValue) {
		
		Map param = new HashMap();
		param.put("uid", uid);
		param.put("firstIndex", (page) * rowsPerPage);
		param.put("rowsPerPage", rowsPerPage);
		param.put("searchValue", searchValue);
		System.out.println((page) * rowsPerPage);
		
		return sqlSession.selectList("inquire.selectInquireList", param);		
	}
	
	//get seller answer list
	public Map selectAnswer(String inquireId, String sellerUid) {
		
		Map param = new HashMap();
		param.put("inquireId", inquireId);
		param.put("sellerUid", sellerUid);
		
		return sqlSession.selectOne("inquire.selectAnswer", param);		
	}

	//count inquire list
	public int selectCount(String uid, String searchValue) {
		Map param = new HashMap();
		param.put("uid", uid);
		param.put("searchValue", searchValue);
		System.out.println(searchValue);
		return sqlSession.selectOne("inquire.selectCount", param);
	}
}
