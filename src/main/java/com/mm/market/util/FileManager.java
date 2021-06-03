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
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public String save(String filePath, MultipartFile multipartFile, HttpSession session) throws Exception {
		
		String path = "classpath:/static";
		
		File file = new File(resourceLoader.getResource(path).getFile(), filePath);
		System.out.println(file.getAbsolutePath());
		if(!file.exists()) {
			file.mkdir();
		}
		
		String fileName=UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		file = new File(file, fileName);
		multipartFile.transferTo(file);
		
		return fileName;
	}
	
	
	public boolean delete(String filePath, String fileName, HttpSession session) throws Exception {
		
		String path = "classpath:/static";
		File file = new File(path, fileName);
		
		boolean deleted = false;
		if(file.exists()) {
			deleted = file.delete();
		}
		
		return deleted;
		
	}
}
