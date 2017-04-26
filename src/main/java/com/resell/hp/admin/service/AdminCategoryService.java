package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminCategoryDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class AdminCategoryService {

	@Autowired
	private AdminCategoryDAO adminCategoryDAO;
	
	public List getList() {
		return adminCategoryDAO.selectList();
	}
	
	public Map get(String categoryId) {
		return adminCategoryDAO.selectOne(categoryId);
	}
	
	public int modify(String categoryId, String categoryName) {
		return adminCategoryDAO.update(categoryId, categoryName);
	}	
	
	public int remove(String categoryId) {
		return adminCategoryDAO.delete(categoryId);
	}
	
	public int add(String categoryName) {
		String categoryId = KeyUtils.generateKey("CAT");
		return adminCategoryDAO.insert(categoryId, categoryName);
	}	
}
