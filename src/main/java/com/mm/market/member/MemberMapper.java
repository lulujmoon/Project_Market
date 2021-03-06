package com.mm.market.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sun.el.stream.Optional;

@Mapper
public interface MemberMapper {

	public int setJoin(MemberVO memberVO) throws Exception;
	
	public int setJoinFile(MemberFileVO memberFileVO)throws Exception;
	
	public int setMemberRole(Map<String, String> map) throws Exception;
	
	public MemberVO getSelectByUsername(MemberVO memberVO);
	
	public MemberVO getSelectByCode(MemberVO memberVO) throws Exception;
	
	public MemberVO idCheck(String username) throws Exception;
	
	public int setUpdate(MemberVO memberVO) throws Exception;

	public MemberFileVO selectFile(MemberVO memberVO)throws Exception;
	
	public int setDeleteFile (MemberFileVO memberFileVO) throws Exception;
	
	public int setDelete(MemberVO memberVO) throws Exception;
	
	public MemberVO getEmail(MemberVO memberVO) throws Exception;
}
