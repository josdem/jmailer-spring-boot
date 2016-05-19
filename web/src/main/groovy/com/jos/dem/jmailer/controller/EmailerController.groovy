/*
  Copyright 2016 José Luis De la Cruz Morales joseluis.delacruz@gmail.com

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

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