package com.resell.hp.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList() {
		return sqlSession.selectList("admin.adminMember.selectList");
	}
	
	public Map selectUserData(String userUid) {
		return sqlSession.selectOne("admin.adminMember.selectUserData", userUid);
	}
}
