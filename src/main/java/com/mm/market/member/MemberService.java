package com.mm.market.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public int setJoin(MemberVO memberVO)throws Exception{
		int result = memberMapper.setJoin(memberVO);
		
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		memberVO = memberMapper.getLogin(memberVO);
		
		return memberVO;
	}
	
	
}
