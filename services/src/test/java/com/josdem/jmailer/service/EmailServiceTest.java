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

package com.josdem.jmailer.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.josdem.jmailer.command.Command;
import com.josdem.jmailer.service.impl.EmailerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Slf4j
class EmailServiceTest {

  private EmailerService emailerService;

  @Mock private MessageService messageService;
  @Mock private EmailValidatorService emailValidatorService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    emailerService = new EmailerServiceImpl(messageService, emailValidatorService);
  }

  @Test
  @DisplayName("send a message")
  void shouldSendMessage(TestInfo testInfo) {
    log.info("Running {}", testInfo.getDisplayName());
    Command command = mock(Command.class);
    emailerService.sendEmail(command);
    verify(messageService).message(command);
    verify(emailValidatorService).validate(command);
  }
}
