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
	
	
}
