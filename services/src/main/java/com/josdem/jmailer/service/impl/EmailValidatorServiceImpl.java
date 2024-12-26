/*
  Copyright 2024 Jose Morales joseluis.delacruz@gmail.com

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

package com.josdem.jmailer.service.impl;

import com.josdem.jmailer.command.Command;
import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.config.EmailProperties;
import com.josdem.jmailer.exception.BusinessException;
import com.josdem.jmailer.service.EmailValidatorService;
import com.josdem.jmailer.service.FilterService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailValidatorServiceImpl implements EmailValidatorService {

  private static final String REGEX = "[0-9]+";
  private final EmailProperties emailProperties;
  private final FilterService filterService;

  @Override
  public void validate(Command command) {
    MessageCommand messageCommand = (MessageCommand) command;
    validateContainSpaces(messageCommand.getMessage());
    validateMessage(messageCommand.getMessage());
    validateName(messageCommand.getName());
    validateByKeyword(messageCommand.getName());
  }

    private void validateByKeyword(@NotNull String name) {
        if(!filterService.isValidUser(name)){
            throw new BusinessException("Spam detected by keyword: " + name);
        }
    }

    private void validateContainSpaces(String message) {
      if(message.matches(REGEX)){
          return;
      }
      if(!message.contains(" ") && !message.contains("/")){
          throw new BusinessException("Spam message detected: " + message);
      }
  }

  private void validateMessage(String message) {
    emailProperties
        .getSpamTokens()
        .forEach(
            token -> {
              if (message.contains(token)) {
                throw new BusinessException("Spam token detected: " + token);
              }
            });
  }

  private void validateName(String name) {
    emailProperties
        .getSpamNames()
        .forEach(
            token -> {
              if (name.equalsIgnoreCase(token)) {
                throw new BusinessException("Spam name detected: " + token);
              }
            });
  }
}
