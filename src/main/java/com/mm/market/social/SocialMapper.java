package com.mm.market.social;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.Pager;

@Mapper
public interface SocialMapper {

	//List
	public List<SocialVO> getList(Pager pager) throws Exception;
	//Count
	public Long getTotalCount(Pager pager) throws Exception;
	//Select
	public SocialVO getSelect(SocialVO socialVO) throws Exception;
	//Insert
	public int setInsert(SocialVO socialVO) throws Exception;
	//FileInsert
	public int setFileInsert(SocialFileVO socialFileVO) throws Exception;
	//Update
	public int setUpdate(SocialVO socialVO) throws Exception;
	//Delete
	public int setDelete(SocialVO socialVO) throws Exception;
	
}
