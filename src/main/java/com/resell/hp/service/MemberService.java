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
		/*String encryptedUserPw = memberDAO.selectUserPw(userId);*/
		/*return passwordEncoder.matches(pw, encryptedUserPw);*/
		return true;
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


	public String getTempPw(String userId) {
		String uid = memberDAO.selectUid(userId);
		memberDAO.updateTempPw(uid);
		
		return memberDAO.selectUserPw(userId);
	}
	
	public Map getUserInfo(String uid) {		
		return memberDAO.selectUserInfo(uid);
	}

	

}
