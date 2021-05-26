package com.mm.market.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{

	private String username;
	private String password;
	private String name;
	private String phone;
	private String email;
	private Date joinDate;
	
	private boolean enabled;
	
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
