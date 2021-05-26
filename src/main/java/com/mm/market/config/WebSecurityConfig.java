package com.mm.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		web.ignoring().antMatchers("/css/**","/script/**","/img/**","/resources/**","/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.exceptionHandling()
		.accessDeniedPage(null)
		.accessDeniedHandler(null)
		.and()
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.and()
		.formLogin()
		.loginPage("/member/memberLogin")
		.defaultSuccessUrl("/member/memberLoginResult")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/member/memberLogout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.deleteCookies("JESSIONID")
		.permitAll()
		;
		
		
	}


}
