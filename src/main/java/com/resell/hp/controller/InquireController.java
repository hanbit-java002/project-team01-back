package com.resell.hp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.annotation.SignInRequired;
import com.resell.hp.service.InquireService;

@RestController
@RequestMapping("/api/inquire")
public class InquireController {

	@Autowired
	private InquireService inquireService;
	
	//get inquire list
	@SignInRequired
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public Map inquireList(HttpSession session,
			@RequestParam("rowsPerPage") int rowsPerPage,
			@RequestParam("page") int page,
			@RequestParam("searchValue") String searchValue) {
		
		String uid = (String) session.getAttribute("uid");
		
		List list = inquireService.selectInquireList(uid, rowsPerPage, page, searchValue);
		int count = inquireService.selectCount(uid, searchValue);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		return result;
	}
	
	//get seller answer list
	@SignInRequired
	@RequestMapping(value="/answer", method = RequestMethod.GET)
	public Map getAnswer(
			@RequestParam("inquireId") String inquireId,
			@RequestParam("sellerUid") String sellerUid) {
		
		return inquireService.selectAnswer(inquireId, sellerUid);
	}
		
}
