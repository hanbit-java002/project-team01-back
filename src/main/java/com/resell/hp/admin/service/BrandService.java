package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.BrandDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class BrandService {
	
	@Autowired
	private BrandDAO brandDAO;
	
	public List getList() {
		return brandDAO.selectList();
	}
	
	public Map get(String brandId) {
		return brandDAO.selectOne(brandId);
	}
	
	public int modify(String brandId, String brandName) {
		return brandDAO.update(brandId, brandName);
	}	
	
	public int remove(String brandId) {
		return brandDAO.delete(brandId);
	}
	
	public int add(String brandName) {
		String brandId = KeyUtils.generateKey("BRD");
		return brandDAO.insert(brandId, brandName);
	}	
}
