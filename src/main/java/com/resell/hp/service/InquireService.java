package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.InquireDAO;

@Service
public class InquireService {

	@Autowired
	private InquireDAO inquireDAO;

	//get inquire list
	public List selectInquireList(String uid, int rowsPerPage, int page, String searchValue){
		return inquireDAO.selectInquireList(uid, rowsPerPage, page, searchValue);
	}
	
	//get seller answer list
		public Map selectAnswer(String inquireId, String sellerUid){
			return inquireDAO.selectAnswer(inquireId, sellerUid);
		}

	//count inquire list
	public int selectCount(String uid, String searchValue) {
		return inquireDAO.selectCount(uid, searchValue);
	}
}
