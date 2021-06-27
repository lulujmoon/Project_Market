package com.mm.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mm.market.security.LoginFail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/favicon.ico","/script/**","/img/**","/resources/**","/js/**","/error");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		//데이터를 볼때 내가 보낸 토큰이 맞는지 보증하는거
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/member/login","/member/join","/member/search","/member/approve").permitAll()
		.antMatchers("/product/select/**","/product/list").permitAll()
		.antMatchers("/notice/list","/notice/select").permitAll()
		.antMatchers("/notice/**").hasAnyRole("ADMIN")
		.antMatchers("/member/auth/kakao/callback").permitAll()
		.antMatchers("/mail/**").permitAll()
		.anyRequest().authenticated()
		.and()
		
		//로그인관련
		.formLogin()
		.loginPage("/member/login")
		//로그인 성공시
		.defaultSuccessUrl("/member/loginResult")
		//로그인 실패시 처리
		.failureHandler(new LoginFail())
		.permitAll()
		.and()	
		//로그아웃관련
		.logout()
		.logoutUrl("/member/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.deleteCookies("JESSIONID")
		.permitAll()
		;
		
		
	}

@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
	// TODO Auto-generated method stub
	return super.authenticationManagerBean();
}

}
