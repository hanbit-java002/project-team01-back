package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminProductDAO;
import com.resell.hp.dao.HitsDAO;
import com.resell.hp.service.FileService;

@Service
public class AdminProductService {
	@Autowired
	private AdminProductDAO adminProductDAO;
	@Autowired
	private FileService fileService;
	@Autowired
	private HitsDAO hitsDAO;

	
	
	public List<Map<String,Object>> selectProductList(Map filterInfo){
		return adminProductDAO.selectProductList(filterInfo);
	}



	public int selectCount(Map filterInfo) {
		return adminProductDAO.selectCount(filterInfo);
	}
}
