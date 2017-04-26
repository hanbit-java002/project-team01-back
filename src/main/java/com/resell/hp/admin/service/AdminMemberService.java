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
	
	public List getList() {
		return adminMemberDAO.selectList();
	}
	
	public Map getUserData(String userUid) {
		return adminMemberDAO.selectUserData(userUid);
	}
}
