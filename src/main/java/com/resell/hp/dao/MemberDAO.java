package com.resell.hp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberDAO.class);
	
	public void insertUser(Map userInfo) {
		sqlSession.selectOne("member.insertUser", userInfo);
	}
	
	public void insertUserDetail(Map userInfoDetail) {
		sqlSession.selectOne("member.insertUserDetail", userInfoDetail);
	}
	
	public String selectUserPw(String userId){
		return sqlSession.selectOne("member.selectUserPw", userId);
	}
	
	public String selectUid(String userId) {
		return sqlSession.selectOne("member.selectUid", userId);
	}

	public String selectRank(String uid) {
		return sqlSession.selectOne("member.selectRank", uid);
	}
	
	public String selectUserId(String userName, String phoneNum) {
		Map param = new HashMap();
		param.put("userName", userName);
		param.put("phoneNum", phoneNum);
		
		return sqlSession.selectOne("member.selectUserId", param);
	}

}
