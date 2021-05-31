package com.mm.market.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager1 {
	
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
		
		System.out.println("파일 저장 경로 : "+path);
		
		return fileName;
	}
	
	public boolean delete(String dirName, String fileName, HttpSession session) throws Exception {
		
		String path = session.getServletContext().getRealPath("resources/upload/"+dirName);
		File file = new File(path, fileName);
		
		boolean deleted = false;
		if(file.exists()) {
			deleted = file.delete();
		}
		
		return deleted;
		
	}

}
