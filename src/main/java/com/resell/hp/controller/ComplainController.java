package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.ComplainService;

@RestController
@RequestMapping("/api/complain")
public class ComplainController {
	
	@Autowired
	private ComplainService complainService;
	

	// complain 추가
	@RequestMapping(value="/add/{productId}", method=RequestMethod.GET)
	public Map addLike(HttpSession session, @PathVariable("productId") String productId) {
		try {
			String uid = (String) session.getAttribute("uid");
			complainService.addComplain(productId, uid);
			complainService.updateStatus(productId);
			
			Map result = new HashMap();
			result.put("result", "ok");
			return result;
			
		} catch (Exception e) {
			Map result = new HashMap();
			result.put("result", "no");
			return result;
		}
	}
	
	//has complain
	@RequestMapping(value="/hasComplain/{productId}", method=RequestMethod.GET)
	public Map hasComplain(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		boolean hasComplain = complainService.hasComplain(productId, uid);
		
		Map result = new HashMap();
		result.put("result", hasComplain);
		
		return result;
	}
}
