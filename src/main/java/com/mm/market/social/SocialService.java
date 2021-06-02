package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;

@Service
public class SocialService {
	
	@Autowired
	private SocialMapper socialMapper;
	
	@Autowired
	private FileManager fileManager;

	public List<SocialVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		Long totalCount = socialMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		return socialMapper.getList(pager);
	}

	public List<SocialVO> getCategoryList(Pager pager)throws Exception {
		return socialMapper.getCategoryList(pager);
	}

	public SocialVO getSelect(SocialVO socialVO) throws Exception {
		return socialMapper.getSelect(socialVO);
	}

	public int setInsert(SocialVO socialVO, MultipartFile [] files) throws Exception {
		int result = socialMapper.setInsert(socialVO);
		String filePath = "upload/social/";

		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()==0) {
				continue;
			}
			String fileName = fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			SocialFileVO socialFileVO = new SocialFileVO();
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(multipartFile.getOriginalFilename());
			socialFileVO.setSocialNum(socialVO.getSocialNum());
			socialMapper.setFileInsert(socialFileVO);
		}
		
		return result; 
	}

	public int setUpdate(SocialVO socialVO) throws Exception {
		return socialMapper.setUpdate(socialVO);
	}

	public int setDelete(SocialVO socialVO) throws Exception {
		return socialMapper.setDelete(socialVO);
	}
}
