package com.mm.market.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.Pager;

@Service
public class SocialService {
	
	@Autowired
	private SocialMapper socialMapper;

	public List<SocialVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		Long totalCount = socialMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		return socialMapper.getList(pager);
	}

	public SocialVO getSelect(SocialVO socialVO) throws Exception {
		return socialMapper.getSelect(socialVO);
	}

	public int setInsert(SocialVO socialVO) throws Exception {
		return socialMapper.setInsert(socialVO);
	}

	public int setUpdate(SocialVO socialVO) throws Exception {
		return socialMapper.setUpdate(socialVO);
	}

	public int setDelete(SocialVO socialVO) throws Exception {
		return socialMapper.setDelete(socialVO);
	}
}
