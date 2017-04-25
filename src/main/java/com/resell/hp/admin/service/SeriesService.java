package com.resell.hp.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.admin.dao.BrandDAO;
import com.resell.hp.admin.dao.SeriesDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class SeriesService {

	@Autowired
	private SeriesDAO seriesDAO;
	
	@Autowired
	private BrandDAO brandDAO;
	
	public List getList() {
		return seriesDAO.selectList();
	}
	
	public Map get(String seriesId) {
		return seriesDAO.selectOne(seriesId);
	}
	
	public int modify(String seriesId, String seriesName) {
		return seriesDAO.update(seriesId, seriesName);
	}	
	
	public int remove(String seriesId) {
		return seriesDAO.delete(seriesId);
	}
	
	public int add(String seriesName) {
		String seriesId = KeyUtils.generateKey("SRS");
		String brandId = brandDAO.selectBrandId("NIKE");
		
		return seriesDAO.insert(seriesId, seriesName, brandId);
	}	
}
