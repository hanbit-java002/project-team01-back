package com.resell.hp.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.admin.service.AdminBrandService;

@RestController
@RequestMapping("/api/admin/brand")
public class AdminBrandController {
	
	@Autowired
	private AdminBrandService adminBrandService;
	
	@RequestMapping("/list")
	public Map getlist(@RequestParam("currentPage") int currentPage,
			@RequestParam("rowsPerPage") int rowsPerPage) {
		
		List list = adminBrandService.getList(currentPage, rowsPerPage);
		int count = adminBrandService.countList();
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		
		return result;
	}

	@RequestMapping("/add")
	public Map add(@RequestParam("brandName") String brandName) {
		adminBrandService.add(brandName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.GET)
	public Map get(@PathVariable("brandId") String brandId) {
		
		return adminBrandService.get(brandId);
	}
			
	@RequestMapping(value="/{brandId}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("brandId") String brandId,
			@RequestParam("brandName") String brandName) {
		
		adminBrandService.modify(brandId, brandName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.DELETE)
	public Map remove(@PathVariable("brandId") String brandId) {
		
		adminBrandService.remove(brandId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
