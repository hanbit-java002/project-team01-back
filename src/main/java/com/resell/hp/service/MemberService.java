package com.resell.hp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.resell.hp.controller.MemberController;
import com.resell.hp.dao.MemberDAO;

@Service
public class MemberService {
	private static final String SECRET_KEY = "hanbit";
	private PasswordEncoder passwordEncoder = new StandardPasswordEncoder(SECRET_KEY);
	
	@Autowired
	private MemberDAO memberDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);
	
	public boolean isValidMember(String userId, String userPw) {
		/*String encryptedUserPw = memberDAO.selectUserPw(userId);*/
		/*return passwordEncoder.matches(pw, encryptedUserPw);*/
		return true;
	}
	
	public String getUid(String userId) {
		return memberDAO.selectUid(userId);
	}

	

}
