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
import com.resell.hp.admin.service.SeriesService;

@RestController
@RequestMapping("/api/admin/series")
public class SeriesController {

	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/list")
	public List list() {
		return seriesService.getList();
	}

	@RequestMapping("/add")
	public Map add(@RequestParam("seriesName") String seriesName, @RequestParam("brandId") String brandId) {
		seriesService.add(seriesName, brandId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{seriesId}", method=RequestMethod.GET)
	public Map get(@PathVariable("seriesId") String seriesId) {
		
		return seriesService.get(seriesId);
	}
	
	
	@RequestMapping(value="/{seriesId}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("seriesId") String seriesId,
			@RequestParam("seriesName") String seriesName) {
		
		seriesService.modify(seriesId, seriesName);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	@RequestMapping(value="/{seriesId}", method=RequestMethod.DELETE)
	public Map remove(@PathVariable("seriesId") String seriesId) {
		
		seriesService.remove(seriesId);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
