/*
 Copyright 2025 Jose Morales contact@josdem.io

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

package com.josdem.jmailer.controller;

import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.config.EmailProperties;
import com.josdem.jmailer.exception.BusinessException;
import com.josdem.jmailer.service.EmailerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Tag(name = "email", description = "Knows how to send emails")
@RequestMapping("/emailer/*")
@RestController
@RequiredArgsConstructor
public class EmailerController {

  private final EmailerService emailerService;
  private final EmailProperties emailProperties;

  @Value("${token}")
  private String token;

  @Operation(summary = "Send an email with JSON")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "User created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Something went wrong")
      })
  @PostMapping(value = "/message", consumes = "application/json")
  public ResponseEntity<String> message(@RequestBody MessageCommand command) {
    log.info("Request contact email: {}", command.getEmail());
    if (!token.equals(command.getToken())) {
      return new ResponseEntity<String>("FORBIDDEN", HttpStatus.FORBIDDEN);
    }
    emailerService.sendEmail(command);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @PostMapping(value = "/form", consumes = "application/x-www-form-urlencoded")
  public ModelAndView form(@Valid MessageCommand command) {
    log.info("Request message from: {}", command);
    if (!token.equals(command.getToken())) {
      log.info("Invalid user's token");
      return new ModelAndView("redirect:/contact");
    }
    emailerService.sendEmail(command);
    return new ModelAndView("redirect:" + emailProperties.getRedirect());
  }

  @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handleException(BusinessException be) {
    return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
