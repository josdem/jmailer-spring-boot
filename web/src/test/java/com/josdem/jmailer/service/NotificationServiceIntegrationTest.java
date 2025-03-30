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

import com.josdem.jmailer.command.MessageCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class NotificationServiceIntegrationTest {

  private final NotificationService notificationService;

  @DisplayName("send an email")
  @Test
  void shouldSendEmail(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand message = new MessageCommand();
    message.setEmail("joseluis.delacruz@gmail.com");
    message.setSubject("Hello from Jmailer!");
    message.setMessage("Hello there, this is a Junit5 message.");
    message.setName("Junit Jupiter");
    message.setTemplate("message.ftl");
    message.setToken("userToken");
    notificationService.sendNotification(message);
  }
}
