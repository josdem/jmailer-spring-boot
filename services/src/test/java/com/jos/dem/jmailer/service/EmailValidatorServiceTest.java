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

package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.config.EmailProperties;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.impl.EmailValidatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class EmailValidatorServiceTest {

  private EmailValidatorService emailValidatorService;
  private final EmailProperties emailProperties = new EmailProperties();

  private final List<String> spamTokens = Arrays.asList("one", "two", "three");
  private final List<String> spamNames = Arrays.asList("John", "Edward", "Sebastian");

  @BeforeEach
  void setup() {
    emailValidatorService = new EmailValidatorServiceImpl(emailProperties);
    emailProperties.setSpamNames(spamNames);
    emailProperties.setSpamTokens(spamTokens);
  }

  @Test
  @DisplayName("validating spam by message")
  void shouldFilterSpamByMessage(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand command = getMessageCommand();
    command.setMessage("one");
    assertThrows(BusinessException.class, () -> emailValidatorService.validate(command));
  }

  @Test
  @DisplayName("validating spam by name")
  void shouldFilterSpamByName(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand command = getMessageCommand();
    command.setMessage("Hello from Junit5!");
    command.setName("John");
    assertThrows(BusinessException.class, () -> emailValidatorService.validate(command));
  }

  @Test
  @DisplayName("validating adoption by message")
  void shouldSendAdoptionTemplate(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand command = getMessageCommand();
    command.setMessage("5610184061");
    emailValidatorService.validate(command);
  }

  private MessageCommand getMessageCommand() {
    MessageCommand command = new MessageCommand();
    command.setName("josdem");
    command.setToken("userToken");
    command.setTemplate("message.ftl");
    command.setEmail("contact@josdem.io");
    command.setRedirect("redirect");
    command.setSource("source");
    return command;
  }
}
