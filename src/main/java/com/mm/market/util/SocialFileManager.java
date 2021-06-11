package com.mm.market.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SocialFileManager {
	
	@Autowired
	public ResourceLoader resourceLoader;
	
	public String save(String dirName, MultipartFile multipartFile, HttpSession session) throws Exception {
		
		String path = session.getServletContext().getRealPath("resources/upload/"+dirName);
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String fileName = "";
		fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		file = new File(file, fileName);
		multipartFile.transferTo(file);
		
		return fileName;
	}
	
	public boolean delete(String dirName, String fileName, HttpSession session) throws Exception {
		
		String path = session.getServletContext().getRealPath("resources/upload/"+dirName);
		File file = new File(path, fileName);
		
		boolean check = false;
		if(file.exists()) {
			check = file.delete();
		}
		
		return check;
		
	}

}
