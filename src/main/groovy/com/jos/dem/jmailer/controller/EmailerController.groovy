package com.jos.dem.jmailer.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.EmailerService

@RestController
class EmailerController {

  @Autowired
  EmailerService emailerService

  @RequestMapping("/")
  String index() {
    emailerService.sendEmail()
  }

}
