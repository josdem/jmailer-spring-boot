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

package com.josdem.jmailer.service;

import com.josdem.jmailer.service.impl.MailServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.IOException;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MailServiceTest {

  private static final String TEMPLATE = "message.ftl";

  private MailService mailService;
  private final Map model = mock(Map.class);
  private final Configuration configuration = mock(Configuration.class);
  private final JavaMailSender javaMailSender = mock(JavaMailSender.class);
  private final Template freeMarkerTemplate = mock(Template.class);
  private final Map<String, String> values =
      Map.of("email", "contact@josdem.io", "subject", "Hello from Jmailer!");

  @BeforeEach
  void setup() {
    mailService = new MailServiceImpl(configuration, javaMailSender);
  }

  @Test
  @DisplayName("sending mail with template")
  void shouldSendMailWithTemplate() throws IOException {
    given(configuration.getTemplate(TEMPLATE)).willReturn(freeMarkerTemplate);
    mailService.sendMailWithTemplate(values, model, TEMPLATE);
    verify(javaMailSender).send(isA(MimeMessagePreparator.class));
  }
}
