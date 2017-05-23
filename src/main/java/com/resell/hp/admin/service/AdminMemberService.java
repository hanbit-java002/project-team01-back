package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminMemberDAO;

@Service
public class AdminMemberService {
	
	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	public List getList(int currentPage, int rowsPerPage, String sValue) {
		return adminMemberDAO.selectList(currentPage, rowsPerPage, sValue);
	}
	
	public int countList(String sValue) {
		return adminMemberDAO.countList(sValue);
	}
	
	public Map getUserData(String userUid) {
		return adminMemberDAO.selectUserData(userUid);
	}
	
	public int modify(String userUid, String userName, String userRank, String userPhoneNum, 
			String userAddr, String userAddrDetail, String userZipCode) {
		
		return adminMemberDAO.update(userUid, userName, userRank, 
				userPhoneNum, userAddr, userAddrDetail, userZipCode);
	}	
	
	
	
	/* 회원 삭제(탈퇴) */
	public int drop(String uid) {	
		return adminMemberDAO.drop(uid);	
	}
}
