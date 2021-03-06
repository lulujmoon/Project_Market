package com.mm.market.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	
	public String save(String dirName, MultipartFile multipartFile, HttpSession session) throws Exception {
		
		String path = session.getServletContext().getRealPath("resources/upload/"+dirName);
		
		File file = new File(path);
		System.out.println(file.getAbsolutePath());
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String fileName=UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		if(multipartFile.getOriginalFilename()!=null) {
			file = new File(file, fileName);
			multipartFile.transferTo(file);

		}else {
			file.delete();
		}
		return fileName;
	}
	
	
	public boolean delete(String name, String fileName, HttpSession session) throws Exception {
		
		String path = session.getServletContext().getRealPath("resources/upload/"+name);
		File file = new File(path, fileName);
		
//		System.out.println("FM.path : "+ path);
//		System.out.println("FM.file : "+ file);
		
		boolean deleted = false;
		if(file.exists()) {
			deleted = file.delete();
		}
//		System.out.println("FM.deleted : "+deleted);
		return deleted;
		
	}

}
