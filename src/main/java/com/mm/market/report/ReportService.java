package com.mm.market.report;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "lemonmarket03@gmail.com";

    public void mailSend(ReportVO reportVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ReportService.FROM_ADDRESS);
        message.setFrom(reportVO.getAddress());
        message.setSubject(reportVO.getTitle());
        message.setText(reportVO.getMessage());

        mailSender.send(message);
    }
}