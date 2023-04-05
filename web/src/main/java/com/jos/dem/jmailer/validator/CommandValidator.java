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

package com.jos.dem.jmailer.validator;

import com.jos.dem.jmailer.command.FormCommand;
import com.jos.dem.jmailer.config.EmailProperties;
import com.jos.dem.jmailer.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CommandValidator implements Validator {

  private final EmailProperties emailProperties;

  @Override
  public boolean supports(Class<?> clazz) {
    return FormCommand.class.equals(clazz);
  }

  @Override
  public void validate(Object command, Errors errors) {
    FormCommand messageCommand = (FormCommand) command;
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
