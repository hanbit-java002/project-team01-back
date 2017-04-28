package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.AdminBrandDAO;
import com.resell.hp.admin.dao.AdminSeriesDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class AdminSeriesService {

	@Autowired
	private AdminSeriesDAO adminSeriesDAO;
	
	@Autowired
	private AdminBrandDAO adminBrandDAO;
	
	public List getList(int currentPage, int rowsPerPage) {
		return adminSeriesDAO.selectList(currentPage, rowsPerPage);
	}
	
	public int countList() {
		return adminSeriesDAO.countList();
	}
	
	public Map get(String seriesId) {
		return adminSeriesDAO.selectOne(seriesId);
	}
	
	public int modify(String seriesId, String seriesName) {
		return adminSeriesDAO.update(seriesId, seriesName);
	}	
	
	public int remove(String seriesId) {
		return adminSeriesDAO.delete(seriesId);
	}
	
	public int add(String seriesName) {
		String seriesId = KeyUtils.generateKey("SRS");
		String brandId = adminBrandDAO.selectBrandId("NIKE");
		
		return adminSeriesDAO.insert(seriesId, seriesName, brandId);
	}	
}
