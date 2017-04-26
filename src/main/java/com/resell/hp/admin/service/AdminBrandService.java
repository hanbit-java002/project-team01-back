package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminBrandDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class AdminBrandService {
	
	@Autowired
	private AdminBrandDAO adminBrandDAO;
	
	public List getList() {
		return adminBrandDAO.selectList();
	}
	
	public Map get(String brandId) {
		return adminBrandDAO.selectOne(brandId);
	}
	
	public String getBrandId(String brandName){
		return adminBrandDAO.selectBrandId(brandName);
	}
	
	public int modify(String brandId, String brandName) {
		return adminBrandDAO.update(brandId, brandName);
	}	
	
	public int remove(String brandId) {
		return adminBrandDAO.delete(brandId);
	}
	
	public int add(String brandName) {
		String brandId = KeyUtils.generateKey("BRD");
		return adminBrandDAO.insert(brandId, brandName);
	}	
}
