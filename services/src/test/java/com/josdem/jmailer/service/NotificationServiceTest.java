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
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.config.SubjectConfig;
import com.josdem.jmailer.service.impl.NotificationServiceImpl;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationServiceTest {
  public static final String EMAIL = "contact@josdem.io";
  public static final String SUBJECT = "Hello from Jmailer!";
  public static final String TEMPLATE = "message.ftl";

  private NotificationService notificationService;

  private MailService mailService = mock(MailService.class);
  private ObjectMapper mapper = mock(ObjectMapper.class);

  @BeforeEach
  void setup() {
    notificationService = new NotificationServiceImpl(mailService, mapper);
  }

  @Test
  @DisplayName("send a notification")
  void shouldSendNotification() {
    Map model = mock(Map.class);
    Map data = Map.of("email", EMAIL, "subject", SUBJECT);
    MessageCommand messageCommand =
        new MessageCommand(
            "Jose Morales",
            EMAIL,
            "token",
            "Hello from Jmailer!",
            "contactName",
            "emailContact",
            TEMPLATE,
            "redirect",
            "source");
    when(mapper.convertValue(messageCommand, Map.class)).thenReturn(model);
    notificationService.sendNotification(messageCommand);
    verify(mailService).sendMailWithTemplate(data, model, TEMPLATE);
  }
}
