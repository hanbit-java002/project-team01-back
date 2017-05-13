package com.resell.hp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resell.hp.service.FileService;

@Repository
public class FileDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(String imgId, String contentType, int imgSize, 
			String imgUrl, String productId, boolean isMainImg){
		Map imgInfo = new HashMap();
		
		imgInfo.put("imgId", imgId);
		imgInfo.put("contentType", contentType);
		imgInfo.put("imgSize", imgSize);
		imgInfo.put("imgUrl", imgUrl);
		imgInfo.put("productId", productId);
		imgInfo.put("isMainImg", isMainImg);
		
		return sqlSession.insert("file.insert",imgInfo);
	}
	public Map selectOne(String fileId) {
		
		return sqlSession.selectOne("file.selectOne", fileId); 
	}
	public List selectImgs(String productId) {
		return sqlSession.selectList("file.selectImgs", productId); 
	}
	public void deleteImg(String imgId) {
		System.out.println(imgId);
		sqlSession.delete("file.deleteImg", imgId); 
	}
	public void updateSetMainImg(String imgId) {
		sqlSession.update("file.updateSetMainImg", imgId); 
	}
	public void updateRomoveMainImg(String imgId) {
		sqlSession.update("file.updateRomoveMainImg", imgId); 
	}
	public void deleteProduct(String productId) {
		System.out.println("파일: "+ productId);
		sqlSession.delete("file.deleteProduct", productId); 
		
	}

}
