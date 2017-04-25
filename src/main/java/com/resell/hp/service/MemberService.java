package com.resell.hp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.MemberDAO;

@Service
public class MemberService {
	private static final String SECRET_KEY = "hanbit";
	private PasswordEncoder passwordEncoder = new StandardPasswordEncoder(SECRET_KEY);
	private MemberDAO memberDAO;
	
	public boolean isValidMember(String id, String pw) {
		String encryptedUserPw = memberDAO.selectUserPw(id);
		/*return passwordEncoder.matches(pw, encryptedUserPw);*/
		return true;
	}

	

}
