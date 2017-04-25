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

import com.resell.hp.admin.service.BrandService;

@RestController
@RequestMapping("/api/admin/brand")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/list")
	public List list() {
		return brandService.getList();
	}
	

	@RequestMapping("/add")
	public Map add(@RequestParam("brandName") String brandName) {
		brandService.add(brandName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.GET)
	public Map get(@PathVariable("brandId") String brandId) {
		
		return brandService.get(brandId);
	}
			
	@RequestMapping(value="/{brandId}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("brandId") String brandId,
			@RequestParam("brandName") String brandName) {
		
		brandService.modify(brandId, brandName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{brandId}", method=RequestMethod.DELETE)
	public Map remove(@PathVariable("brandId") String brandId) {
		
		brandService.remove(brandId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
