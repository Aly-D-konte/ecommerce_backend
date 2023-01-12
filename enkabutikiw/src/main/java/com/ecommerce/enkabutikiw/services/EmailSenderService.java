package com.ecommerce.enkabutikiw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
//Pour envoyer plusieurs mail
    public void sendEmailToMultipleRecipients(String subject, String text, List<String> recipients) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(recipients.toArray(new String[0]));
        mailSender.send(message);
    }



    //Pour envoyer un seul mail


//    public void sendSimpleEmail(String toEmail,
//                                String subject,
//                                String body
//    ) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("alykonte19@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//        mailSender.send(message);
//        System.out.println("Mail envoyer avec succes...");
//
//
//    }
}
