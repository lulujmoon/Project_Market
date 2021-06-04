package com.mm.market.social;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
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
	private SqlSession session;

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

	@Transactional(rollbackFor = Exception.class)
	public int setInsert(SocialVO socialVO, MultipartFile [] files) throws Exception {
		int result = socialMapper.setInsert(socialVO);

		for(MultipartFile f:files) {
			SocialFileVO socialFileVO = new SocialFileVO();
			String fileName = fileManager.save(f, "social");
			System.out.println(fileName);
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(f.getOriginalFilename());
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
