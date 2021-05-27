package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class SocialService {
	
	@Autowired
	private SocialMapper socialMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${social.filePath}")
	private String filePath;

	public List<SocialVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		Long totalCount = socialMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		return socialMapper.getList(pager);
	}

	public SocialVO getSelect(SocialVO socialVO) throws Exception {
		return socialMapper.getSelect(socialVO);
	}

	@Transactional(rollbackFor = Exception.class)
	public int setInsert(SocialVO boardVO, MultipartFile [] files) throws Exception {
		int result = socialMapper.setInsert(boardVO);

		// 예외는 발생하지 않고 결과가 0이 나올 경우 
		// 강제로 예외 발생
		if(result<1) {
			throw new Exception();
		}
		
		String filePath= this.filePath;
		
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize() == 0) {
				continue;
			}
			
			String fileName= fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			SocialFileVO socialFileVO = new SocialFileVO();
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(multipartFile.getOriginalFilename());
			socialFileVO.setSocialNum(boardVO.getSocialNum());
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
