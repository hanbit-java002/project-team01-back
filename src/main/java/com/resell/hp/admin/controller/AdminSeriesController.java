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
import com.resell.hp.admin.service.AdminSeriesService;

@RestController
@RequestMapping("/api/admin/series")
public class AdminSeriesController {

	@Autowired
	private AdminSeriesService adminSeriesService;
	
	@RequestMapping("/list")
	public Map getlist(@RequestParam("currentPage") int currentPage,
			@RequestParam("rowsPerPage") int rowsPerPage) {
		
		List list = adminSeriesService.getList(currentPage, rowsPerPage);
		int count = adminSeriesService.countList();
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		
		return result;
	}
	
	@RequestMapping("/add")
	public Map add(@RequestParam("seriesName") String seriesName) {
		adminSeriesService.add(seriesName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{seriesId}", method=RequestMethod.GET)
	public Map get(@PathVariable("seriesId") String seriesId) {
		
		return adminSeriesService.get(seriesId);
	}
		
	@RequestMapping(value="/{seriesId}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("seriesId") String seriesId,
			@RequestParam("seriesName") String seriesName) {
		
		adminSeriesService.modify(seriesId, seriesName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{seriesId}", method=RequestMethod.DELETE)
	public Map remove(@PathVariable("seriesId") String seriesId) {
		
		adminSeriesService.remove(seriesId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
