package com.orderly.controller;

import com.orderly.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation(value = "/orderly/mail", tags = "Mail Controller", notes = "Mail API")
@RestController("/orderly/mail")
public class MailController {

    private final MailService service;

    public MailController(MailService service) {
        this.service = service;
    }

    @ApiOperation(value = "Send Mail", response = Boolean.class)
    @GetMapping("/sendMail")
    public Boolean sendMail(){
        return service.sendMail();
    }
}
