package com.jos.dem.jmailer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.stereotype.Controller
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.service.EmailerFormatter
import com.jos.dem.jmailer.command.MessageCommand

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
class EmailerController {

  @Autowired
  EmailerService emailerService
  @Autowired
  EmailerFormatter emailerFormatter

  @Value('${email.redirect}')
  String redirectUrl

  Log log = LogFactory.getLog(this.class)

  @RequestMapping("/")
  String index() {
    "index"
  }

  @RequestMapping("/command-line")
  String commandLine() {
    "command_line"
  }

  @RequestMapping("/form")
  String form() {
    "form"
  }

  @RequestMapping("/batch")
  String batch() {
    "batch"
  }

  @RequestMapping("/contact")
  String contact() {
    "contact"
  }

  @RequestMapping(method = POST, value = "/message", consumes="application/json")
  @ResponseBody
  ResponseEntity<String> message(@RequestBody MessageCommand command) {
    log.info "Sending contact email: ${command.email}"
    emailerService.sendEmail(command)
    new ResponseEntity<String>("OK", HttpStatus.OK)
  }

  @RequestMapping(method = POST,  value = "/form")
  String form(MessageCommand command) {
    log.info "Sending email to: ${command.email}"
    emailerService.sendEmail(emailerFormatter.format(command))
    return "redirect:${redirectUrl}"
  }

}
