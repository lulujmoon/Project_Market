package com.mm.market.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;



@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private HttpSession session;


	//개발자가 호출x는 login메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		memberVO = memberMapper.getLogin(memberVO);

		System.out.println("로드유저");
		System.out.println(memberVO.getAuthorities());
		return memberVO;
	}

	public MemberVO findMember(MemberVO memberVO)throws Exception {
		MemberVO checkMember = memberMapper.getUsername(memberVO);

		return checkMember;
	}


	//검증메서드
	public boolean memberError(MemberVO memberVO, Errors errors) throws Exception{
		boolean result = false;
	
		result = errors.hasErrors();

	//password일치
	if(!memberVO.getPassword().equals(memberVO.getPassword1())) {
		errors.rejectValue("password1", "memberVO.password.notEqual");
		//formpath,field명, properties의 code(key)
		result = true; }

		//username 중복
		//MemberVO checkMember = memberMapper.getUsername(memberVO);
		//if(checkMember != null) {errors.rejectValue("username", "memberVO.id.equal");
		//result = true;
		//} 
		return result;
		}

	public MemberVO idCheck(String username)throws Exception{
		return memberMapper.idCheck(username);
	
	}

	//예외가 발생했으면 자동으로 rollback
	@Transactional(rollbackFor = Exception.class)
	public int setJoin(MemberVO memberVO,MultipartFile multipartFile)throws Exception{
		//password 암호
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		//계정활성화
		memberVO.setEnabled(true);

		//member table
		int result = memberMapper.setJoin(memberVO);

		//role table
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", memberVO.getUsername()); //키값 ,밸류값
		map.put("roleName", "ROLE_MEMBER");
		result = memberMapper.setMemberRole(map);
		
		//hdd file
		String filePath="upload/member/";
		if(multipartFile.getSize() !=0) {
			String fileName= fileManager.save(filePath, multipartFile, session);
			System.out.println(fileName);
			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setFileName(fileName);
			memberFileVO.setOriginName(multipartFile.getOriginalFilename());
			memberFileVO.setUsername(memberVO.getUsername());
		//3. MemberFiles table 저장
			result = memberMapper.setJoinFile(memberFileVO);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setKakaoJoin(MemberVO memberVO)throws Exception{
		//password 암호
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		//계정활성화
		memberVO.setEnabled(true);

		//member table
		int result = memberMapper.setJoin(memberVO);

		//role table
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", memberVO.getUsername()); //키값 ,밸류값
		map.put("roleName", "ROLE_MEMBER");
		result = memberMapper.setMemberRole(map);

		return result;
	}
	
	public int setUpdate(MemberVO memberVO)throws Exception{
		if(memberVO.getPassword()!="") {
			memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));			
		}else {
			MemberVO memberVO2 = memberMapper.getUsername(memberVO);
			memberVO.setPassword(memberVO2.getPassword());
		}

		
		int result = memberMapper.setUpdate(memberVO);
		
		return result;
	}
	
	public MemberFileVO selectFile(MemberVO memberVO) throws Exception{
		return memberMapper.selectFile(memberVO);
	}
	
	public int setUpdateFile(MultipartFile multipartFile,MemberVO memberVO, Authentication authentication)throws Exception{
		int result=0;
		memberVO =(MemberVO)authentication.getPrincipal();
		
		if(multipartFile.getOriginalFilename().length()!=0) {
			MemberFileVO memberFileVO = memberMapper.selectFile(memberVO);
			
			if(memberFileVO!=null) {
				String delFileName = memberFileVO.getFileName();
				boolean check = fileManager.delete("magazineT", delFileName, session);

				memberFileVO.setFileName(delFileName);
				memberMapper.setDeleteFile(memberFileVO);				
			}

			String filePath="upload/member/";
			String fileName= fileManager.save(filePath, multipartFile, session);
			System.out.println(fileName);
			MemberFileVO memberFileVO2 = new MemberFileVO();
			memberFileVO.setFileName(fileName);
			memberFileVO.setOriginName(multipartFile.getOriginalFilename());

			
			result = memberMapper.setJoinFile(memberFileVO);
				
	}
		return result;
	}

}
