package com.mm.market.memberLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLocationService {

	@Autowired
	private MemberLocationMapper memberLocationMapper;
	
	public String getList(MemberLocationVO memberLocationVO) throws Exception {
		return memberLocationMapper.getList(memberLocationVO);
	}
	
	public int setInsert(MemberLocationVO memberLocationVO) throws Exception {
		return memberLocationMapper.setInsert(memberLocationVO);
	}
	
	public int setDelete(MemberLocationVO memberLocationVO) throws Exception {
		return memberLocationMapper.setDelete(memberLocationVO);
	}
	
}
