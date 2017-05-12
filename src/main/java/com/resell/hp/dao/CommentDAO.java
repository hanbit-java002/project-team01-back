package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSession sqlSession;  
	
	//write comment
	public int addComment(String productId, String uid, String commentText, String commentId) {		
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		param.put("commentText", commentText);
		param.put("commentId", commentId);
		
		return sqlSession.insert("comment.addComment", param);
	}
	
	//write reply
	public int addReply(String productId, String uid, String replyText, String commentId, String upperId) {		
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		param.put("replyText", replyText);
		param.put("commentId", commentId);
		param.put("upperId", upperId);
		
		return sqlSession.insert("comment.addReply", param);
	}
	
	//get comment
	public List getComment(String productId) {	
		
		return sqlSession.selectList("comment.selectComment", productId);
	}
	
	//remove comment
	public int delete(String productId, String commentId, String uid) {
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("uid", uid);
		param.put("commentId", commentId);
		
		return sqlSession.delete("comment.delete", param);
	}
	
	//update comment
	public int update(String productId, String commentId, String newCommentText, String uid) {
		Map param = new HashMap();
		param.put("productId", productId);
		param.put("commentId", commentId);
		param.put("uid", uid);
		param.put("newCommentText", newCommentText);
		
		return sqlSession.update("comment.update", param);
	}
}
