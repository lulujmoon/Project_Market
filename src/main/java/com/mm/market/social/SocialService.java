package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;

@Service
public class SocialService {
	
	@Autowired
	private SocialMapper socialMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private HttpSession session;

	public List<SocialVO> getList(Pager pager) throws Exception {
		Long perPage = 20L;
		Long perBlock = 5L;

		pager.makeRow(perPage);
		Long totalCount = socialMapper.getTotalCount(pager);
		pager.makeNum(totalCount, perPage, perBlock);

		return socialMapper.getList(pager);
	}

	public List<SocialVO> getCategoryList(Pager pager) throws Exception {
		return socialMapper.getCategoryList(pager);
	}

	public SocialVO getSelect(SocialVO socialVO) throws Exception {
		return socialMapper.getSelect(socialVO);
	}

	@Transactional(rollbackFor = Exception.class)
	public int setInsert(SocialVO socialVO, MultipartFile [] file) throws Exception {
		int result = socialMapper.setInsert(socialVO);
		
		long socialNum = socialMapper.getSocialNum();
		socialVO.setSocialNum(socialNum);

		for(MultipartFile mf:file) {
			SocialFileVO socialFileVO = new SocialFileVO();
			String fileName = fileManager.save("social", mf, session);

			socialFileVO.setSocialNum(socialNum);
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(mf.getOriginalFilename());

			socialMapper.setFileInsert(socialFileVO);
		}
		
		return result; 
	}

	public int setUpdate(SocialVO socialVO, MultipartFile file) throws Exception {
		int result = 0;
		
		if(file.getOriginalFilename().length()!=0) {
			String fileName = fileManager.save("social", file, session);
			
			SocialFileVO socialFileVO = new SocialFileVO();
			socialFileVO.setSocialNum(socialVO.getSocialNum());
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(file.getOriginalFilename());
			
			result = socialMapper.setUpdate(socialVO);
			result = socialMapper.setFileInsert(socialFileVO);
		} else {
			result = socialMapper.setUpdate(socialVO);
		}
		return socialMapper.setUpdate(socialVO);
	}

	public int setDelete(SocialVO socialVO) throws Exception {
		socialVO = socialMapper.getSelect(socialVO);
		
		if(socialVO.getFiles().size()!=0) {
			for(int i=0;i<socialVO.getFiles().size();i++) {
				SocialFileVO socialFileVO = socialVO.getFiles().get(i);
				boolean deleted = fileManager.delete("social", socialVO.getFiles().get(i).getFileName(), session);
				System.out.println("delete : "+deleted);
			}
		}
		
		return socialMapper.setDelete(socialVO);
	}
	
	public int setFileDelete(SocialFileVO socialFileVO) throws Exception {
		socialFileVO = socialMapper.setFileSelect(socialFileVO);

		int result = socialMapper.setFileDelete(socialFileVO);

		if(result>0) {
			boolean deleted = fileManager.delete("social", socialFileVO.getFileName(), session);
			System.out.println("delete : "+deleted);
			System.out.println("fileName : "+socialFileVO.getFileName());
		}
		
		return result;
	}
}
