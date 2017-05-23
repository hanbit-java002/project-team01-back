package com.resell.hp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	public Map signIn(@RequestParam("userId") String userId, 
					  @RequestParam("userPw") String userPw,
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
         
         result.put("userId", session.getAttribute("uid"));
         result.put("userId", session.getAttribute("userId"));
         result.put("userRank", session.getAttribute("userRank"));
      }
      
      result.put("result", signedIn);

      result.put("userRank", session.getAttribute("userRank"));
      result.put("uid", session.getAttribute("uid"));
 
      return result;
   }
   
   @RequestMapping("/api/member/signout")
   public Map signOut(HttpSession session) {
	      session.invalidate();
	      
	      Map result = new HashMap();
	      result.put("result", "ok");
	      
	      return result; 
   }
   
   @RequestMapping("/api/member/getUserInfo")
   public Map getUserInfo(HttpSession session) {
	   String uid =(String) session.getAttribute("uid");
	   return memberService.getUserInfo(uid);
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
   
   
   private String from = "hanbitresell@gmail.com";	//보내는사람 이메일
   private String subject = "[Resll] 임시 비밀번호 입니다.";	//제목
   
   @RequestMapping(value = "/api/member/findPw", method=RequestMethod.POST)
   public Map sendMail(@RequestParam("userEmail") String userId) {
	   
	   String uid = memberService.getUid(userId);
	   //String tempPw = memberService.getTempPw(userId);	//임시 비밀번호
	   int num;
	   Random random = new Random();
	   num = random.nextInt(999999) + 100000;
	   
	   String tempPw = "temp" + num;
	   
	   memberService.updateTempPw(userId, tempPw);
	   
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
   }
   

   /* 회원정보 변경 */  
   //회원정보 받아오기
   @RequestMapping(value="/api/member/{userUid}", method=RequestMethod.GET)
	public Map get(@PathVariable("userUid") String userUid) {
	   
	   return memberService.getUserData(userUid);
	}
   
   //회원정보 수정(비밀번호 변경x)
   @RequestMapping(value="/api/member/{userUid}", method=RequestMethod.PUT)
	public Map modify(@PathVariable("userUid") String userUid,
			@RequestParam("userName") String userName,
			@RequestParam("userPhoneNum") String userPhoneNum,
			@RequestParam("userAddr") String userAddr,
			@RequestParam("userAddrDetail") String userAddrDetail,
			@RequestParam("userZipCode") String userZipCode) {
		
	   memberService.modify(userUid, userName, userPhoneNum, userAddr, userAddrDetail, userZipCode);

	   Map result = new HashMap();
	   result.put(result, "ok");
	
	   return result;
   }
   
   //회원정보 수정(비밀번호 변경o)
   @RequestMapping(value="/api/member/{userUid}", method=RequestMethod.POST)
	public Map modifyPw(@PathVariable("userUid") String userUid,
			@RequestParam("modifyPw") String modifyPw,
			@RequestParam("userName") String userName,
			@RequestParam("userPhoneNum") String userPhoneNum,
			@RequestParam("userAddr") String userAddr,
			@RequestParam("userAddrDetail") String userAddrDetail,
			@RequestParam("userZipCode") String userZipCode) {

	   memberService.modifyPw(userUid, modifyPw, userName, userPhoneNum, userAddr, userAddrDetail, userZipCode);

	   Map result = new HashMap();
	   result.put(result, "ok");
	
	   return result;
	}
   
   
   
    /* 회원 삭제(탈퇴) */
	@RequestMapping(value="/api/member/delete", method=RequestMethod.POST)
	public Map remove(@RequestParam("userUid") String userUid,
					  @RequestParam("userPw") String userPw, 
					  HttpSession session) {
		
		String userId = memberService.getUserId(userUid);

		
		try {
			
	        if(!memberService.isValidMember(userId, userPw)) {
	            LOGGER.warn("패스워드 틀림:" + userId + "/" + userPw);
	             
	            throw new RuntimeException("패스워드가 다릅니다.");
	        }
	    }
	    catch (NullPointerException e) {
	          throw new RuntimeException(e);
	    }
	
		memberService.drop(userUid);
		
		session.invalidate();	//세션 끊기(로그아웃)
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	   
	   @RequestMapping(value = "/api/member/emailConfirmMail", method=RequestMethod.POST)
	   public Map sendMailConfirmMail(@RequestParam("userEmail") String userId, HttpSession session) {
		   String subject = "[Resll] 인증확인 이메일 입니다.";			   
		   Random random = new Random();
		   String num = String.format("%06d",(random.nextInt(1000000)));
		   
		   String cfmNum= "CFM" + num;
		   String isExist= memberService.getUid(userId);
		   if( !(isExist == null || "".equals(isExist))) {
			   throw new RuntimeException("가입된 아이디입니다.");
		   }
		   else {
			   Map result = new HashMap<>();
			   
			   try {
					   MimeMessage message = mailSender.createMimeMessage();
					   MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); 
					   messageHelper.setTo(userId);	//받는사람 이메일 
					   messageHelper.setText(userId + " 님의 \n 인증번호는 [ " + cfmNum + " ] 입니다."
					   		+ "\n\n* 인증번호를 입력해주세요."); 	//본문
					   messageHelper.setFrom(from); 
					   messageHelper.setSubject(subject);	// 메일제목은 생략 가능하다
						   
					   mailSender.send(message); 
				} 
				catch(Exception e) { 
					   System.out.println(e); 
				}
			   session.setAttribute("emailCfm", cfmNum);
			   
			   	result.put("result", "ok");			   	
			   	return result; 
		   }
	   }
	   
	   @RequestMapping(value = "/api/member/emailConfirm", method=RequestMethod.POST)
	   public Map sendMailConfirm(@RequestParam("cfm") String cfm ,HttpSession session) {
		   Map result = new HashMap();
		   String emailCfm =(String) session.getAttribute("emailCfm");
		   if (emailCfm.equals(cfm)) {
			   
			   result.put("result", "ok");
		   }
		   else {
			   result.put("result", "no");
			   throw new RuntimeException("틀린 인증번호 입니다.");
		   }
		   return result; 
	   }

}