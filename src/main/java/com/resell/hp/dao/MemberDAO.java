package com.resell.hp.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	private SqlSession sqlSession;
	
	public String selectUserPw(String id){
		return sqlSession.selectOne("member.selectUserPw", id);
	}

}
