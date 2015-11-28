package com.jos.dem.jmailer.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
class SimpleController {

  @RequestMapping("/")
  String index() {
    "Greetings from Spring Boot!"
  }

}
