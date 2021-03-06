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

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.ExceptionHandler

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.exception.BusinessException

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Api(description="Knows how to send emails")
@RequestMapping("/emailer/*")
@RestController
class EmailerController {

  @Autowired
  EmailerService emailerService

  @Value('${email.redirect}')
  String redirectUrl

  Logger logger = LoggerFactory.getLogger(this.class)

  @RequestMapping(method = POST, value = "/message", consumes="application/json")
  ResponseEntity<String> message(@RequestBody MessageCommand command) {
    logger.info "Sending contact email: ${command.email}"
    emailerService.sendEmail(command)
    new ResponseEntity<String>("OK", HttpStatus.OK)
  }

  @ApiImplicitParams([
  @ApiImplicitParam(name = "email", value = "email-to@domain", required = true, dataType = "string", paramType = "query"),
  @ApiImplicitParam(name = "message", value = "message body", required = true, dataType = "string", paramType = "query"),
  @ApiImplicitParam(name = "name", value = "sender name", required = true, dataType = "string", paramType = "query"),
  @ApiImplicitParam(name = "emailContact", value = "email-reference@domain", required = true, dataType = "string", paramType = "query"),
  @ApiImplicitParam(name = "source", value = "source", required = true, dataType = "string", paramType = "query"),
  ])
  @RequestMapping(method = POST,  value = "/form", consumes="application/x-www-form-urlencoded")
  ModelAndView form(MessageCommand command) {
    logger.info "Sending email: ${command.dump()}"
    emailerService.sendEmail(command)
    return new ModelAndView("redirect:${command.redirect}")
  }

  @ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized")
  @ExceptionHandler(BusinessException.class)
  ResponseEntity<String> handleException(BusinessException be){
    return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED)
  }

}
