package com.mm.market.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{

	@NotBlank(message = "id를 입력하세요.")
	private String username;
	@NotBlank(message="pw를 입력하세요.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
			 message="비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상 포함된 8~20자의 비밀번호여야 합니다.")
	private String password;
	@NotBlank(message="이름을 입력하세요")
	private String name;
	@NotBlank(message="전화번호를 입력하세요")
	private String phone;
	@NotBlank(message="이메일을 입력하세요")
	private String email;
	
	private boolean enabled;
	private Date joinDate;
	
	//role
	private List<RoleVO> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//계정이 가지고 있는 권한을 목록으로 리턴
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(RoleVO roleVO:this.roles) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지 
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않은지
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는지
		return true;
	}
	@Override
	public boolean isEnabled() {
		// 계정이 활성화(사용가능)인지
		return this.enabled;
	}
}