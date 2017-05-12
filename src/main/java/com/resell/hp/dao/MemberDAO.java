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

	public int updateTempPw(String uid) {

		return sqlSession.update("member.updateTempPw", uid);
	}

	
	
	/* 회원정보변경 */ 
	public Map selectUserData(String userUid) {
		return sqlSession.selectOne("member.selectUserData", userUid);
	}
	
	public int update(String userUid, String userName, String userPhoneNum,
			String userAddr, String userAddrDetail, String userZipCode) {
		
		Map param = new HashMap();
		param.put("userUid", userUid);
		param.put("userName", userName);
		param.put("userPhoneNum", userPhoneNum);
		param.put("userAddr", userAddr);
		param.put("userAddrDetail", userAddrDetail);
		param.put("userZipCode", userZipCode);
		
		return sqlSession.insert("member.update", param);
	}
	
	public int updatePw(String userUid, String modifyPw, String userName, String userPhoneNum, 
			String userAddr, String userAddrDetail, String userZipCode) {
				
		Map param = new HashMap();
		param.put("userUid", userUid);
		param.put("modifyPw", modifyPw);
		
		param.put("userName", userName);
		param.put("userPhoneNum", userPhoneNum);
		param.put("userAddr", userAddr);
		param.put("userAddrDetail", userAddrDetail);
		param.put("userZipCode", userZipCode);
		
		
		return sqlSession.insert("member.updatePw", param);
	}
	
	public Map selectUserInfo(String uid) {

		return sqlSession.selectOne("member.selectUserInfo", uid);
	}

}