package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private HttpSession session;

	@Value("${social.filePath}")
	private String filePath;

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

	public int setInsert(SocialVO socialVO, MultipartFile [] files) throws Exception {
		int result = socialMapper.setInsert(socialVO);

		String filePath = this.filePath;

		for(MultipartFile mf:files) {

			if(mf.getSize() == 0) {
				continue;
			}

			String fileName = fileManager.save("social", mf, session);

			SocialFileVO socialFileVO = new SocialFileVO();
			socialFileVO.setFileName(fileName);
			socialFileVO.setOriginName(mf.getOriginalFilename());
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

	public String setSummerFileUpload(MultipartFile file) throws Exception {
		String fileName = fileManager.save("social", file, session);
		return fileName;

	}

	public Boolean setSummerFileDelete(String fileName) throws Exception {
		boolean result = fileManager.delete("social", fileName, session);
		return result;
	}
}
