package com.jos.dem.jmailer.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.google.gson.Gson

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.command.MessageType

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@RestController
class EmailerController {

  @Autowired
  EmailerService emailerService

  @ResponseBody
  ResponseEntity<String> message(@RequestBody String json) {
    MessageCommand command = new Gson().fromJson(json, MessageCommand.class)
    log.info "Sending contact email: ${command.email}"
    command.type = MessageType.MESSAGE
    emailerService.sendEmail(command)
    new ResponseEntity<String>("OK", HttpStatus.OK)
  }

}
