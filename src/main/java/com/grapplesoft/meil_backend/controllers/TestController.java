package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.models.request.BaseSendMail;
import com.grapplesoft.meil_backend.services.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController extends  BaseController{
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/send-mail", consumes = "application/json", produces = "application/json")
    public void sendMail(@RequestBody BaseSendMail request) {
        this.mailService.sendMail(request.subject(), request.receipent(), request.message());
    }
}
