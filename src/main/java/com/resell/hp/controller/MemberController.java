package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.controller.MemberController;
import com.resell.hp.service.MemberService;

@RestController
public class MemberController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/api/memeber/signIn", method=RequestMethod.POST)
	public Map signIn(@RequestParam("id") String id, @RequestParam("pw") String pw,
			HttpServletRequest request) {
		
	      try {
	          if(!memberService.isValidMember(id, pw)) {
	            LOGGER.warn("패스워드 틀림:" + id + "/" + pw);
	             
	             throw new RuntimeException("패스워드가 다릅니다.");
	          }
	       }
	       catch (NullPointerException e) {
	          throw new RuntimeException("가입되지 않은 사용자입니다.");
	       }
		
		Map result = new HashMap();
		result.put("result", "ok");		
		return result;
	}
	
   @RequestMapping("/api/member/signedin")
   public Map signedin(HttpSession session) {
      Map result = new HashMap();
      String signedIn = "no";      
      
      if (session.getAttribute("signedIn") != null &&
            (Boolean) session.getAttribute("signedIn")) {
         signedIn = "yes";
         
         result.put("userId", session.getAttribute("userId"));
      }
      
      result.put("result", signedIn);
      
      return result;
   }

}
