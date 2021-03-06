package com.mm.market.member;

import java.net.PasswordAuthentication;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.market.mail.MailController;
import com.mm.market.memberLocation.MemberLocationService;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.product.HeartVO;
import com.mm.market.product.ProductService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberLocationService memberLocationService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	/*
	 * @GetMapping("error") public String error() { return "error/error"; }
	 */

	@GetMapping("login")
	public String getLogin()throws Exception{
		return "member/login";
	}
	
	  @PostMapping("login") 
	  public String getLogin(HttpServletRequest request)throws Exception{
		  //???????????? ?????????????????? ????????????????????? ?????????
	  System.out.println(request.getAttribute("message"));
	  
	  return "member/login"; 
	  }
	 

	@GetMapping("loginFail")
	public String loginFail()throws Exception{
		return "/member/login";
	}

	@GetMapping("loginResult")
	public String memberLoginResult(HttpSession session, Authentication auth2)throws Exception{

		Enumeration<String> en = session.getAttributeNames();
		MemberVO memberVO = new MemberVO();

		while(en.hasMoreElements()) {
			System.out.println("attribute Name:+"+en.nextElement());
		}

		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");

		SecurityContextImpl sc = (SecurityContextImpl)obj;
									//???????????? session??? ??????
		Authentication auth = sc.getAuthentication();
		
		return "redirect:/";

	}

	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		System.out.println("????????????");
		session.invalidate();

		return "redirect:../";
	}


	@GetMapping("join")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/join";
	}

	@GetMapping("approve")
	public void setApprove()throws Exception{
			
	}

	@PostMapping("approve")
	public String setApprove(Model model)throws Exception{
			
		return "redirect:/member/join";
	}
	
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO, Errors errors, Model model, MultipartFile avatar) throws Exception{
		System.out.println("Join process"+ memberVO.getName().length());

		  if(memberService.memberError(memberVO, errors)) { 
			  model.addAttribute("checked", "checked");
		  return"member/join"; 
		  
		  }

		int result = memberService.setJoin(memberVO, avatar);

		return "redirect:../";

	}
	
	@PostMapping("idCheck")
	public ModelAndView idCheck(String username, ModelAndView mv)throws Exception{
		MemberVO idCheck = memberService.idCheck(username);	
		int result =0;
		if(idCheck != null) {
			result =1;
		}
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
	@GetMapping("info")
	public void infomation(Authentication authentication, HttpSession session, @ModelAttribute MemberVO memberVO)throws Exception{
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();

		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(userDetails.getUsername());
		List<MemberLocationVO> list = memberLocationService.getList(memberLocationVO);
		
		session.setAttribute("locations", list);
	}
	
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO,Errors errors, HttpSession session, Authentication authentication) throws Exception{

		int result = memberService.setUpdate(memberVO);
		//db??? ??????????????? session??? ????????????

		/*
		 * if(errors.hasErrors()) { return "member/info"; }
		 */
		MemberVO old =(MemberVO)authentication.getPrincipal();
		
		old.setPassword(memberVO.getPassword());
		old.setName(memberVO.getName());
		old.setPhone(memberVO.getPhone());
		old.setEmail(memberVO.getEmail());
		
		return "redirect:./info";
	}

		
	@GetMapping("auth/kakao/callback")
	public String kakaoCallback(String code,MultipartFile avatar) throws Exception {
		
		
		//post???????????? key=value ???????????? ??????(??????????????????)
		RestTemplate rt = new RestTemplate();
		
		
		//HttpHeader ???????????? ??????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody ???????????? ??????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "bdf85067bd67f89b950ae22189274a9c");
		params.add("redirect_uri", "http://localhost/member/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader??? Httpbody??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		//Http ???????????? - post???????????? , response??? ?????? ??????
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				
				);
		//Gson,JsonSimple,ObjectMapper....
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("????????????????????????:"+oAuthToken.getAccess_token());
		
		
	
		RestTemplate rt2 = new RestTemplate();
			
		//HttpHeader ???????????? ??????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader??? Httpbody??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);
		
		//Http ???????????? - post???????????? , response??? ?????? ??????
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com//v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
				
				);
		System.out.println(response2.getBody());
		
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//User ???????????? : username,password,email
		System.out.println("????????? ?????????(??????):"+kakaoProfile.getId());
		System.out.println("????????? ?????????(??????):"+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("???????????? ????????????:" + kakaoProfile.getKakao_account().getEmail());
		System.out.println("???????????? ?????????:" + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("???????????? ????????????:"+kakaoProfile.getId());

		MemberVO KakaomemberVO = new MemberVO();
		KakaomemberVO.setUsername(kakaoProfile.getKakao_account().getEmail());
		KakaomemberVO.setPassword(kakaoProfile.getId().toString());
		KakaomemberVO.setEmail(kakaoProfile.getKakao_account().getEmail());
		KakaomemberVO.setName(kakaoProfile.getProperties().getNickname());
		KakaomemberVO.setOauth(true);
		
				
		//????????? ?????? ???????????? ???????????? ??????
		MemberVO originmemberVO = memberService.findMember(KakaomemberVO);
		
		if(originmemberVO==null) {
			try {
				System.out.println("??????????????????->??????????????????");
				int result = memberService.setKakaoJoin(KakaomemberVO);
			
				ModelAndView mv = new ModelAndView();
				mv.addObject("result", result);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
						
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(KakaomemberVO.getUsername(),KakaomemberVO.getPassword() ));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return "redirect:/";
	}
	
	@PostMapping("delete")
	public String setDelete(MemberVO memberVO,Authentication authentication,HttpSession session)throws Exception{
		memberVO =(MemberVO)authentication.getPrincipal();
		
		int result = memberService.setDelete(memberVO);
		session.invalidate();
		
		return "redirect:../";
	}
	
	@GetMapping("search")
	public void getEmail()throws Exception{
		
	}
	
	@PostMapping("search")
	public ModelAndView getEmail(MemberVO memberVO, ModelAndView mv)throws Exception{
		memberVO = memberService.getEmail(memberVO);		
		if(memberVO==null) {
			mv.addObject("alert", "fail");
			mv.setViewName("member/search");
		}else {
		String uuid = UUID.randomUUID().toString();		
		memberVO.setPassword(uuid);		
		memberService.setUpdate(memberVO);
		
		//smtp?????????
		  String host     = "smtp.naver.com";
		  final String user   = "test4913@naver.com";
		  final String password  = "Test4913@";
		  
		  //????????????????????????
		  String to = memberVO.getEmail();
		  
		  // Get the session object
		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");

		  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		   protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		    return new javax.mail.PasswordAuthentication(user, password);
		   }
		  });

		  // Compose the message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(user));
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		   // Subject

		   message.setSubject("market ?????? ???????????? ??????");	   
		   // Text
		   message.setContent(
				   "<h1>"+"??????????????? "+memberVO.getName()+"???,<br> MARKET ?????? ???????????? ?????? ?????? ?????????????????????. "+"</h1>"+
				   "<h3>"+"?????????:"+memberVO.getUsername()+"<br>"+"??????????????????:"+uuid+"</h3>"+
				   "<h5>"+"* ????????????????????? ????????? ????????? ??????????????? ????????? ??????????????????."+"</h5>","text/html; charset=UTF-8");
		   // send the message
		   Transport.send(message);
		   System.out.println("message sent successfully...");
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
		  
		mv.addObject("alert", "success");
		mv.setViewName("member/search");
		}
		return mv;
	}

	//-----------------shop	
			
		@GetMapping("store")
		public ModelAndView store(MemberFileVO memberFileVO,Authentication authentication)throws Exception{
			MemberVO memberVO =(MemberVO)authentication.getPrincipal();
			memberFileVO = memberService.selectFile(memberVO);
		
		
			ModelAndView mv = new ModelAndView();
			mv.addObject("file",memberFileVO);
			mv.setViewName("member/store");
			
			return mv;
		};

}
