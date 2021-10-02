/*
Copyright 2021 Jose Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.controller;

import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.EmailerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "Knows how to send emails")
@RequestMapping("/emailer/*")
@RestController
public class EmailerController {

  @Autowired private EmailerService emailerService;

  @Value("${token}")
  private String token;

  @Value("${email.redirect}")
  private String redirectUrl;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @RequestMapping(method = POST, value = "/message", consumes = "application/json")
  public ResponseEntity<String> message(@RequestBody MessageCommand command) {
    logger.info("Request contact email: " + command.getEmail());
    if (!token.equals(command.getToken())) {
      return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
    }
    emailerService.sendEmail(command);
    return new ResponseEntity<String>("OK", HttpStatus.OK);
  }

  @ApiImplicitParams(
      value = {
        @ApiImplicitParam(
            name = "email",
            value = "email-to@domain",
            required = true,
            dataType = "string",
            paramType = "query"),
        @ApiImplicitParam(
            name = "message",
            value = "message body",
            required = true,
            dataType = "string",
            paramType = "query"),
        @ApiImplicitParam(
            name = "name",
            value = "sender name",
            required = true,
            dataType = "string",
            paramType = "query"),
        @ApiImplicitParam(
            name = "emailContact",
            value = "email-reference@domain",
            required = true,
            dataType = "string",
            paramType = "query"),
        @ApiImplicitParam(
            name = "source",
            value = "source",
            required = true,
            dataType = "string",
            paramType = "query"),
      })
  @RequestMapping(method = POST, value = "/form", consumes = "application/x-www-form-urlencoded")
  public ModelAndView form(MessageCommand command) {
    logger.info("Request message from: ", command.getEmail());
    if (token != command.getToken()) {
      logger.info("Invalid user's token");
      return new ModelAndView("redirect:/contact");
    }
    emailerService.sendEmail(command);
    return new ModelAndView("redirect:" + command.getRedirect());
  }

  @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleException(BusinessException be) {
    return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
