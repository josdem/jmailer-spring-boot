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

import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.EmailerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "email", description = "Knows how to send emails")
@RequestMapping("/emailer/*")
@RestController
@RequiredArgsConstructor
public class EmailerController {

  private final EmailerService emailerService;

  @Value("${token}")
  private String token;

  @Value("${email.redirect}")
  private String redirectUrl;

  @Operation(summary = "Send an email with JSON")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "User created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Something went wrong")
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
  public ModelAndView form(@Valid MessageCommand command) {
    log.info("Request message from: {}", command);
    if (!token.equals(command.getToken())) {
      log.info("Invalid user's token");
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
