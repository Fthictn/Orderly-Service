package com.orderly.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService{

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class.getName());

    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Boolean sendMail() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setTo("fthi.cetin@gmail.com");
            messageHelper.setText("Üyeliğiniz oluşturulmuştur.");
            messageHelper.setSubject("Orderly Üyelik Oluşturma");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        mailSender.send(mimeMessage);
        return true;
    }
}
