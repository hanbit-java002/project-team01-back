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

import com.resell.hp.admin.service.AdminMemberService;

@RestController
@RequestMapping("/api/admin/member")
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService adminMemberService;

	@RequestMapping("/list")
	public List list() {
		return adminMemberService.getList();
	}
	
	@RequestMapping(value="/{userUid}", method=RequestMethod.GET)
	public Map get(@PathVariable("userUid") String userUid) {
		
		return adminMemberService.getUserData(userUid);
	}
	
	@RequestMapping(value="/{userUid}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("userUid") String userUid,
			@RequestParam("userName") String userName,
			@RequestParam("userRank") String userRank,
			@RequestParam("userPhoneNum") String userPhoneNum,
			@RequestParam("userAddr") String userAddr,
			@RequestParam("userAddr") String userAddrDetail,
			@RequestParam("userName") String userZipCode) {
		
		adminMemberService.modify(userUid, userName, userRank, 
				userPhoneNum, userAddr, userAddrDetail, userZipCode);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
}
