package com.resell.hp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
	
	@Autowired
	private BrandService brandService; 
	
	@RequestMapping("/list")
	public List<Map<String, Object>> list() {
		return brandService.getList();
	}

}