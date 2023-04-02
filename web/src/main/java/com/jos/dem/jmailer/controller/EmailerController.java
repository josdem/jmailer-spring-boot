/*
Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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

import com.jos.dem.jmailer.command.FormCommand;
import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.EmailerService;
import com.jos.dem.jmailer.validator.MessageCommandValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Api(tags = "Knows how to send emails")
@RequestMapping("/emailer/*")
@RestController
@RequiredArgsConstructor
public class EmailerController {

  private final EmailerService emailerService;
  private final MessageCommandValidator messageCommandValidator;

  @Value("${token}")
  private String token;

  @Value("${email.redirect}")
  private String redirectUrl;

  @ApiOperation(value = "Send an email with JSON")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "User created"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 500, message = "Something went wrong")
      })
  @RequestMapping(method = POST, value = "/message", consumes = "application/json")
  public ResponseEntity<String> message(@RequestBody MessageCommand command) {
    log.info("Request contact email: {}", command.getEmail());
    if (!token.equals(command.getToken())) {
      return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
    }
    emailerService.sendEmail(command);
    return new ResponseEntity<String>("OK", HttpStatus.OK);
  }

  @RequestMapping(method = POST, value = "/form", consumes = "application/x-www-form-urlencoded")
  public ModelAndView form(@Valid FormCommand command) {
    log.info("Request message from: {}", command);
    if (!token.equals(command.getToken())) {
      log.info("Invalid user's token");
      return new ModelAndView("redirect:/contact");
    }
    messageCommandValidator.validate(command, null);
    emailerService.sendEmail(command);
    return new ModelAndView("redirect:" + command.getRedirect());
  }

  @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleException(BusinessException be) {
    return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
