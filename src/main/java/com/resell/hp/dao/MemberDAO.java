package com.resell.hp.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resell.hp.service.MemberService;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberDAO.class);
	
	public String selectUserPw(String userId){
		return sqlSession.selectOne("member.selectUserPw", userId);
	}
	public String selectUid(String userId) {
		LOGGER.debug(sqlSession.selectOne("member.selectUid", userId));
		return sqlSession.selectOne("member.selectUid", userId);
	}

}
