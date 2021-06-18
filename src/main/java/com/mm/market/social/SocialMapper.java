package com.mm.market.social;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.SocialPager;

@Mapper
public interface SocialMapper {

	//List
	public List<SocialVO> getList(SocialPager socialpager) throws Exception;
	//Category List
	public List<SocialVO> getCategoryList(SocialPager socialpager) throws Exception;
	//Count
	public Long getTotalCount(SocialPager socialpager) throws Exception;
	//Select
	public SocialVO getSelect(SocialVO socialVO) throws Exception;
	//Social Num
	public long getSocialNum() throws Exception;
	//Insert
	public int setInsert(SocialVO socialVO) throws Exception;
	//File Insert
	public int setFileInsert(SocialFileVO socialFileVO) throws Exception;
	//File Delete
	public int setFileDelete(SocialFileVO socialFileVO) throws Exception;
	//File Select
	public SocialFileVO setFileSelect(SocialFileVO socialFileVO) throws Exception;
	//Update
	public int setUpdate(SocialVO socialVO) throws Exception;
	//Delete
	public int setDelete(SocialVO socialVO) throws Exception;
	
	//good
	public int insertGood(GoodVO goodVO) throws Exception;

	public Long getGood(GoodVO goodVO) throws Exception;

	public void updateGood(Long socialNum) throws Exception;

	public int deleteGood(GoodVO goodVO) throws Exception;
	
}