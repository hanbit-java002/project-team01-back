package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.CategoryDAO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	public List<Map<String, Object>> getList() {
		return categoryDAO.selectAll();
	}
	

}
