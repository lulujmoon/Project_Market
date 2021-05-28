package com.mm.market.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

//로그인 실패시 실행하는 클래스객체
@Component
public class LoginFail implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("Login Fail Handler");
				
		String errorClass =exception.getClass().toString().substring(exception.getClass().toString().lastIndexOf(".")+1);
		System.out.println(errorClass);
		
		String message="아이디와 비밀번호를 확인하세요.";
		
		switch(errorClass) {
		case "LockedException":
			message="계정잠김";
			//차단기능 넣으면????????
			break;
		}
		
		//request와 response를 통하여 메세지 뿌려주기	
		request.setAttribute("message", message);
		request.getRequestDispatcher("/member/memberLogin").forward(request, response);
		
	
	
	}
}
