package com.mm.market.socialCategory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialCategoryMapper {
	
	public List<SocialCategoryVO> getList() throws Exception;

}
