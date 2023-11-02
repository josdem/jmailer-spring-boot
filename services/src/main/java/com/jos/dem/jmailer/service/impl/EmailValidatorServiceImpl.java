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

package com.jos.dem.jmailer.service.impl;

import com.jos.dem.jmailer.command.Command;
import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.config.EmailProperties;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.EmailValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailValidatorServiceImpl implements EmailValidatorService {

  private static final String REGEX = "[a-zA-Z]+";
  private final EmailProperties emailProperties;

  @Override
  public void validate(Command command) {
    MessageCommand messageCommand = (MessageCommand) command;
    validateMessage(messageCommand.getMessage());
    validateName(messageCommand.getName());
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
