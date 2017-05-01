package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;

/*import javax.mail.internet.MimeMessage;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.MemberService;

@RestController
public class MemberController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	/* @Autowired
	private JavaMailSender mailSender;*/
	
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
   
   
   /*private String from = "hanbitresell@gmail.com";	//보내는사람 이메일
   private String subject = "[Resll] 임시 비밀번호 입니다.";	//제목
   
   @RequestMapping(value = "/api/member/findPw", method=RequestMethod.POST)
   public Map sendMail(@RequestParam("userEmail") String userId) {
	   
	   String uid = memberService.getUid(userId);
	   String tempPw = memberService.getTempPw(userId);	//임시 비밀번호
	   
	   if(uid == null) {
		   throw new RuntimeException("가입되지 않은 사용자입니다.");
	   }
	   else {
		   Map result = new HashMap<>();
		   
		   try {
				   MimeMessage message = mailSender.createMimeMessage();
				   MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); 
				   messageHelper.setTo(userId);	//받는사람 이메일 
				   messageHelper.setText(userId + " 님의 \n임시 비밀번호는 [ " + tempPw + " ] 입니다."
				   		+ "\n\n* 비밀번호 변경은 [My Page > 회원정보 변경]에서 가능합니다."); 	//본문
				   messageHelper.setFrom(from); 
				   messageHelper.setSubject(subject);	// 메일제목은 생략 가능하다
					   
				   mailSender.send(message); 
			} 
			catch(Exception e) { 
				   System.out.println(e); 
			} 
		   
		   	result.put("result", "ok");
		   	
		   	return result; 
	   }

   }*/
   
}
