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

import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class MessageCommandTest {

  private final MessageCommandValidator validator;

  @Test
  @DisplayName("validating spam by message")
  void shouldFilterSpamByMessage(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand command = new MessageCommand();
    command.setMessage("one");
    command.setName("josdem");
    command.setToken("userToken");
    command.setTemplate("message.ftl");
    command.setEmail("contact@josdem.io");
    Errors errors = mock(Errors.class);
    assertThrows(BusinessException.class, () -> validator.validate(command, errors));
  }
}
