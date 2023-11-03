package com.grapplesoft.meil_backend.services.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MailServiceImpl implements MailService {
    Logger logger = Logger.getLogger(MailServiceImpl.class.getName());

    final private JavaMailSender javaMailSender;

    MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendMail(String subject, String receipent, String message) {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receipent);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        try {
            this.javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error sending mail", e);
        }
    }
}
