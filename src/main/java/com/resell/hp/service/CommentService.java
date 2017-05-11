package com.resell.hp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.CommentDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	//write comment
	@Transactional
	public int addComment(String productId, String uid, String commentText) {
		String commentId = KeyUtils.generateKey("COM");
		return commentDAO.addComment(productId, uid, commentText, commentId);
	}
	
	//write reply
	@Transactional
	public int addReply(String productId, String uid, String replyText, String upperId) {
		String commentId = KeyUtils.generateKey("COM");
		return commentDAO.addReply(productId, uid, replyText, commentId, upperId);
	}
	
	//get comment
	public List getComment(String productId) {
		return commentDAO.getComment(productId);
	}
}
