package com.grapplesoft.meil_backend.services.mailService;


public interface MailService {
    void sendMail(String subject, String receipent, String message);
}
