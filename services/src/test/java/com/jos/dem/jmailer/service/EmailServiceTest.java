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

import com.jos.dem.jmailer.collaborator.CommandValidator;
import com.jos.dem.jmailer.command.Command;
import com.jos.dem.jmailer.service.impl.EmailerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
class EmailServiceTest {

  private EmailerService emailerService;

  @Mock private MessageService messageService;
  @Mock private CommandValidator validator;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    emailerService = new EmailerServiceImpl(messageService, validator);
  }

  @Test
  @DisplayName("send a message")
  void shouldSendMessage(TestInfo testInfo) {
    log.info("Running {}", testInfo.getDisplayName());
    Command command = mock(Command.class);
    when(validator.isValid(command)).thenReturn(true);
    emailerService.sendEmail(command);
    verify(messageService).message(command);
  }
}
