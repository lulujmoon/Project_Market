package com.mm.market.mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/mail/**")
public class MailController {

	@GetMapping("sendMail")
	public String sendMail(MailDTO mailDTO, ModelAndView mv)throws Exception{
		
	//smtp서버명
	  String host     = "smtp.naver.com";
	  final String user   = "test4913@naver.com";
	  final String password  = "Test4913@";
	  
	  //받는사람메일주소
	  String to = "fly5148@naver.com";
	  
	  // Get the session object
	  Properties props = new Properties();
	  props.put("mail.smtp.host", host);
	  props.put("mail.smtp.auth", "true");

	  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(user, password);
	   }
	  });

	  // Compose the message
	  try {
	   MimeMessage message = new MimeMessage(session);
	   message.setFrom(new InternetAddress(user));
	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	   // Subject

	   message.setSubject("제목입니다");	   
	   // Text
	   message.setContent("내용입니다","text/html; charset=UTF-8");
	   // send the message
	   Transport.send(message);
	   System.out.println("message sent successfully...");
	  } catch (MessagingException e) {
	   e.printStackTrace();
	  }	  
	 	  
	 
	  
	  	return "redirect:../"; 
	  
	}

}