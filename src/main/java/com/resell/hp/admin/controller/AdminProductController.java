package com.resell.hp.admin.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.admin.service.AdminProductService;
import com.resell.hp.service.FileService;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminProductController.class);

	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public Map productList(@RequestParam("menuCategory") String menuCategory,
			@RequestParam("rowsPerPage") int rowsPerPage,
			@RequestParam("page") int page) {
	
		
		Map filterInfo = new HashMap<String, Object>();
		filterInfo.put("menuCategory",menuCategory);
		filterInfo.put("rowsPerPage", rowsPerPage);
		filterInfo.put("page", page);
		
		List list = adminProductService.selectProductList(filterInfo);
		int count = adminProductService.selectCount(filterInfo);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		return result;
	}

}
