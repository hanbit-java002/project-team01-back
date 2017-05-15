package com.resell.hp.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resell.hp.dao.FileDAO;
import com.resell.hp.util.KeyUtils;

@Service
public class FileService {
	
	public static final String PATH_PREFIX = "/hanbit/resell/upload/";
	private static final String URL_PREFIX = "/api/file/";
	
	@Autowired
	private FileDAO fileDAO;
	
	@Transactional
	public String addAndSaveProductImg(Map productImgInfo) {
		String[]  arrImgSrc = (String[]) productImgInfo.get("arrImgSrc");
		int mainImgIndex = (Integer) productImgInfo.get("mainImgIndex");
		String productId = (String) productImgInfo.get("productId");
		
		if (arrImgSrc != null) {
			for (int i=0; i<arrImgSrc.length; i++) {
				boolean isMainImg = true;
				String dataUrl = arrImgSrc[i];
				String contentType = StringUtils.substringBetween(dataUrl, "data:", ";base64,");
				String base64 = StringUtils.substringAfter(dataUrl, ";base64,");
				System.out.println(contentType);
				byte[] bytes = Base64.decodeBase64(base64);
				
				int imgSize = bytes.length;
				
				String imgId=KeyUtils.generateKey("IMG");
				
				String imgPath = PATH_PREFIX+imgId;
				String imgUrl = URL_PREFIX+imgId;
				if (i == mainImgIndex) {
					isMainImg = true;
				}
				else {
					isMainImg = false;
				}
				
				addProductImg(imgId, contentType, imgSize, imgUrl, productId, isMainImg);
				
				File file = new File(imgPath);
				
				try {
					FileUtils.writeByteArrayToFile(file, bytes);
				}
				catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return productId;
	}
	@Transactional 
	public void addProductImg(String imgId, String contentType, int imgSize, 
			String imgUrl, String productId , boolean isMainImg) {
		fileDAO.insert(imgId, contentType, imgSize, imgUrl, productId, isMainImg);
	}
	
	public Map get(String fileId) {
		return fileDAO.selectOne(fileId);
	}
	public List getProductImgs(String productId) {
		return fileDAO.selectImgs(productId);
	}
	
	@Transactional
	public void update(Map productImgInfo) {
		String productId= (String) productImgInfo.get("productId");
		String[] arrImgSrc = (String[])productImgInfo.get("arrImgSrc");
		String[] arrDelImgId = (String[])productImgInfo.get("arrDelImgId");
		String mainImg = (String)productImgInfo.get("mainImg");
		String beforeMainImg = (String)productImgInfo.get("beforeMainImg");
		
		
		if (arrDelImgId != null) {
			for (int i = 0; i<arrDelImgId.length; i++) {
				System.out.println(arrDelImgId[i]);
				fileDAO.deleteImg(arrDelImgId[i]);
				String filePath = PATH_PREFIX+arrDelImgId[i];
				File file = new File(filePath);
				
				try {
					FileUtils.forceDelete(file);
				}
				catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
			
		}
		
		
		if (!("default".equals(mainImg))) {
			if (mainImg.contains("IMG")) {
				fileDAO.updateSetMainImg(mainImg);
			}
			fileDAO.updateRomoveMainImg(beforeMainImg);
		}
		
		int mainImgIndex = Integer.MAX_VALUE;
		String tag ="new-image-";
		if (arrImgSrc != null) {
			if (mainImg.contains(tag)){
				System.out.println(mainImg);
				String mainIndex =StringUtils.substringAfterLast(mainImg, tag);
				mainImgIndex = Integer.parseInt(mainIndex);
			}
			
			Map insertImg = new HashMap();
			
			insertImg.put("arrImgSrc", arrImgSrc);
			insertImg.put("productId", productId);
			insertImg.put("mainImgIndex", mainImgIndex);
			
			addAndSaveProductImg(insertImg);
			
		}
	}
	@Transactional
	public void deleteProduct(String productId) {
		List<Map<String,Object>> imgList= fileDAO.selectImgs(productId);
		
		for (Map imgInfo :imgList) {
			String imgId = (String) imgInfo.get("img_id");
			String filePath =PATH_PREFIX+imgId;
			File file = new File(filePath);
			try {
				FileUtils.forceDelete(file);
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		fileDAO.deleteProduct(productId);
		
		
	}
	

}
