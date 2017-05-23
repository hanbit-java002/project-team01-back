package com.resell.hp.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	public Map getList(@RequestParam("currentPage") int currentPage,
			@RequestParam("rowsPerPage") int rowsPerPage,
			@RequestParam("sValue") String sValue) {
		
		List list = adminMemberService.getList(currentPage, rowsPerPage, sValue);
		int count = adminMemberService.countList(sValue);
		
		Map result = new HashMap();
		result.put("list", list);
		result.put("count", count);
		
		return result;
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
			@RequestParam("userAddrDetail") String userAddrDetail,
			@RequestParam("userZipCode") String userZipCode) {
		
		adminMemberService.modify(userUid, userName, userRank, 
				userPhoneNum, userAddr, userAddrDetail, userZipCode);
		
		Map result = new HashMap();
		result.put(result, "ok");
	
		return result;
	}
	
	
    /* 회원 삭제(강제탈퇴) */
	@RequestMapping(value="/{userUid}", method=RequestMethod.DELETE)
	public Map remove(@PathVariable("userUid") String userUid) {

		adminMemberService.drop(userUid);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
}
