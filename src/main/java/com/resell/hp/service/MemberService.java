package com.resell.hp.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.MemberDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class MemberService {
	private static final String SECRET_KEY = "hanbit";
	private PasswordEncoder passwordEncoder = new StandardPasswordEncoder(SECRET_KEY);
	
	@Autowired
	private MemberDAO memberDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void addUser(String userId, String userPw, String userName,
			String userPhone, String userAddr, String userAddrDetail, 
			String userZipcode){
		
		String uid = KeyUtils.generateKey("UID");
		String encryptedUserPw = passwordEncoder.encode(userPw);
		
		Map userInfo = new HashMap();
		userInfo.put("uid", uid);
		userInfo.put("userId", userId);
		userInfo.put("userPw", encryptedUserPw);
		userInfo.put("userName", userName);
		userInfo.put("userRank", "member");
		
		Map userInfoDetail = new HashMap();
		userInfoDetail.put("uid", uid);
		userInfoDetail.put("userPhone", userPhone);
		userInfoDetail.put("userAddr", userAddr);
		userInfoDetail.put("userAddrDetail", userAddrDetail);
		userInfoDetail.put("userZipcode", userZipcode);	
		
		memberDAO.insertUser(userInfo);
		memberDAO.insertUserDetail(userInfoDetail);
	}
	
	
	public boolean isValidMember(String userId, String userPw) {
		String encryptedUserPw = memberDAO.selectUserPw(userId);
		return passwordEncoder.matches(userPw, encryptedUserPw);
		
		//String pw = memberDAO.selectUserPw(userId);
		//return pw.matches(userPw);
	}
	
	public String getUid(String userId) {
		return memberDAO.selectUid(userId);
	}
	
	public String getRank(String uid) {
		return memberDAO.selectRank(uid);
	}
	
	public String getUserId(String userName, String phoneNum) {
		return memberDAO.selectUserId(userName, phoneNum);
	} 
	public String getUserId(String userUid) {
		return memberDAO.selectUserId(userUid);
	} 


	//임시 비밀번호
	public int updateTempPw(String userId, String tempPw) {
		String uid = memberDAO.selectUid(userId);
		String encryptedUserPw = passwordEncoder.encode(tempPw);
		
		return memberDAO.updateTempPw(uid, encryptedUserPw);
	}
	
	public Map getUserInfo(String uid) {		
		return memberDAO.selectUserInfo(uid);
	}


	/* 회원정보변경 */ 
	public Map getUserData(String userUid) {
		return memberDAO.selectUserData(userUid);
	}
	
	public int modify(String userUid, String userName, String userPhoneNum, 
			String userAddr, String userAddrDetail, String userZipCode) {
		
		return memberDAO.update(userUid, userName, userPhoneNum, userAddr, userAddrDetail, userZipCode);
	}
	
	public int modifyPw(String userUid, String modifyPw, String userName, String userPhoneNum, 
			String userAddr, String userAddrDetail, String userZipCode) {
		
		String encryptedUserPw = passwordEncoder.encode(modifyPw);
		return memberDAO.updatePw(userUid, encryptedUserPw, userName, userPhoneNum, userAddr, userAddrDetail, userZipCode);
		
		//return memberDAO.updatePw(userUid, modifyPw, userName, userPhoneNum, userAddr, userAddrDetail, userZipCode);
	}	
	
	
	/* 회원 삭제(탈퇴) */
	public int remove(String uid) {	
		memberDAO.deleteDetail(uid);	//디테일 부터 지운 후
		
		return memberDAO.delete(uid);	//원래 꺼 지운다.
	}

}