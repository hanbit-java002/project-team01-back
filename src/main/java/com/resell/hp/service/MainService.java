package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resell.hp.dao.MainDAO;

@Service
public class MainService {
	
	@Autowired
	private MainDAO mainDAO; 
	
	public List<Map<String, Object>> test() {
		return mainDAO.selectAll();
	}
	

}
