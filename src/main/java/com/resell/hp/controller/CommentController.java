package com.resell.hp.controller;

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

import com.resell.hp.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	//write comment
	@RequestMapping(value="/add/{productId}", method=RequestMethod.GET)
	public Map addComment(HttpSession session, @PathVariable("productId") String productId,
			@RequestParam("commentText") String commentText) {
		String uid = (String) session.getAttribute("uid");
		commentService.addComment(productId, uid, commentText);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	//write reply
	@RequestMapping(value="/addReply/{productId}", method=RequestMethod.GET)
	public Map addReply(HttpSession session, @PathVariable("productId") String productId,
			@RequestParam("replyText") String replyText,
			@RequestParam("upperId") String upperId) {
		String uid = (String) session.getAttribute("uid");
		commentService.addReply(productId, uid, replyText, upperId);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	//get comment
	@RequestMapping(value="/list/{productId}", method=RequestMethod.GET)
	public Map getComment(HttpSession session, @PathVariable("productId") String productId) {
		String uid = (String) session.getAttribute("uid");
		List commentInfo = commentService.getComment(productId);
		
		try {
			Map result = new HashMap();
			result.put("commentInfo", commentInfo);
			result.put("sessionUid", uid);
			return result;		
		} 
		catch (Exception e) {
			Map result = new HashMap();
			result.put("commentInfo", commentInfo);
			result.put("sessionUid", "null");
			return result;
		}
	}
	

}
