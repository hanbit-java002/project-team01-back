package com.resell.hp.admin.dao;

import java.util.HashMap;
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
	
	public int update(String userUid, String userName, String userRank, String userPhoneNum,
			String userAddr, String userAddrDetail, String userZipCode) {
		Map param = new HashMap();
		param.put("userUid", userUid);
		param.put("userName", userName);
		param.put("userRank", userRank);
		param.put("userPhoneNum", userPhoneNum);
		param.put("userAddr", userAddr);
		param.put("userAddrDetail", userAddrDetail);
		param.put("userZipCode", userZipCode);
		
		return sqlSession.insert("admin.adminMember.update", param);
	}
}
