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
	
	@RequestMapping(value="/api/member/signUp", method=RequestMethod.POST)
	public Map signUp(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,
			@RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone,
			@RequestParam("userAddr") String userAddr, @RequestParam("userAddrDetail") String userAddrDetail,
			@RequestParam("userZipcode") String userZipcode) {
		
		memberService.addUser(userId, userPw, userName, userPhone, 
				userAddr, userAddrDetail, userZipcode);
		
		
		Map result = new HashMap();
		result.put("result", "ok");		
		return result;
	}	
	
	@RequestMapping(value="/api/member/signIn", method=RequestMethod.POST)
	public Map signIn(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,
			HttpServletRequest request) {
		
	      try {
	          if(!memberService.isValidMember(userId, userPw)) {
	            LOGGER.warn("패스워드 틀림:" + userId + "/" + userPw);
	             
	             throw new RuntimeException("패스워드가 다릅니다.");
	          }
	       }
	       catch (NullPointerException e) {
	          throw new RuntimeException("가입되지 않은 사용자입니다.");
	       }
	      
	      //로그인 세션 저장//
	      HttpSession session = request.getSession();
	      String uid = memberService.getUid(userId);
	      String userRank = memberService.getRank(uid);
	     
	      session.setAttribute("signedIn", true);
	      session.setAttribute("uid", uid);
	      session.setAttribute("userId", userId);
	      session.setAttribute("userRank", userRank);
		
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
      result.put("userRank", session.getAttribute("userRank"));
      
      return result;
   }
   
   @RequestMapping("/api/member/signout")
   public Map signOut(HttpSession session) {
	      session.invalidate();
	      
	      Map result = new HashMap();
	      result.put("result", "ok");
	      
	      return result;
   }

   
   
   @RequestMapping(value="/api/member/findId", method=RequestMethod.POST)
	public Map findId(@RequestParam("userName") String userName, 
			@RequestParam("phoneNum") String phoneNum) {

	   
	    String userId = memberService.getUserId(userName, phoneNum);
		
	    if(userId == null) {
	    	throw new RuntimeException("가입되지 않은 사용자입니다.");
	    }
	    else {
	    	Map result = new HashMap<>();
			result.put("result", "ok");
			result.put("userId", userId);
			
			return result;
	    }
	}
   
}
