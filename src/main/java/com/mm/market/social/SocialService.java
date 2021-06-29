package com.mm.market.social;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.SocialPager;

@Service
public class SocialService {

	@Autowired
	private SocialMapper socialMapper;

	@Autowired
	private HttpSession session;

	//List
	public List<SocialVO> getList(SocialPager socialPager) throws Exception {
		socialPager.makeRow();
		Long totalCount = socialMapper.getTotalCount(socialPager);
		socialPager.makeNum(totalCount);

		return socialMapper.getList(socialPager);
	}

	public List<SocialVO> getCategoryList(SocialPager socialPager) throws Exception {
		return socialMapper.getCategoryList(socialPager);
	}

	//Select
	public SocialVO getSelect(SocialVO socialVO) throws Exception {
		return socialMapper.getSelect(socialVO);
	}

	//Insert
	public int setInsert(SocialVO socialVO) throws Exception {
		int result = socialMapper.setInsert(socialVO);

		SocialFileVO socialFileVO = new SocialFileVO();
		socialFileVO.setSocialNum(socialVO.getSocialNum());

		return result; 
	}

	//Update
	public int setUpdate(SocialVO socialVO) throws Exception {
		return socialMapper.setUpdate(socialVO);
	}

	//Delete
	public int setDelete(SocialVO socialVO) throws Exception {
		return socialMapper.setDelete(socialVO);
	}

	//good
	public void insertGood(GoodVO goodVO) throws Exception {
		socialMapper.insertGood(goodVO);
		socialMapper.updateGood(goodVO.getSocialNum());
	}
	
	public Long getGood(GoodVO goodVO) throws Exception {
		return socialMapper.getGood(goodVO);
	}
	
	public void deleteGood(GoodVO goodVO) throws Exception {
		socialMapper.deleteGood(goodVO);
		socialMapper.updateGood(goodVO.getSocialNum());
	}
	
	public List<SocialVO> getGoodList(GoodVO goodVO) throws Exception {
		return socialMapper.getGoodList(goodVO);
	}

}