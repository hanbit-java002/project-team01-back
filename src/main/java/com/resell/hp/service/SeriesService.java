package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.SeriesDAO;

@Service
public class SeriesService {
	
	@Autowired
	private SeriesDAO seriesDAO; 
	
	public List<Map<String, Object>> getList() {
		return seriesDAO.selectAll();
	}
	

}
