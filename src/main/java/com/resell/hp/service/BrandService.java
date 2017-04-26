package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.BrandDAO;

@Service
public class BrandService {
	
	@Autowired
	private BrandDAO brandDAO;
	
	public List<Map<String, Object>> getList() {
		return brandDAO.selectAll();
	}
	

}
