package com.mm.market.socialReport;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SocialReportService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "lemonmarket03@gmail.com";

    public void mailSend(SocialReportVO reportVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(SocialReportService.FROM_ADDRESS);
        message.setFrom(reportVO.getAddress());
        message.setSubject(reportVO.getTitle());
        
        String text = "신고자: " +reportVO.getUsername()+"\n";
        text = text + "글 링크: http://localhost/social/select/"+reportVO.getSocialNum()+"\n";
        text = text + "신고 내용: \n" + reportVO.getMessage();

        System.out.println(text);
        message.setText(text);
        
        mailSender.send(message);
    }
}