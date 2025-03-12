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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.josdem.jmailer.model.Client;
import com.josdem.jmailer.service.impl.MailServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

class MailServiceTest {

  private static final String DEFAULT_TEMPLATE = "message.ftl";
  private static final String VETLOG_TEMPLATE = "welcome.ftl";

  private MailService mailService;
  private final Map model = mock(Map.class);
  private final Configuration configuration = mock(Configuration.class);
  private final JavaMailSender javaMailSender = mock(JavaMailSender.class);
  private final JavaMailSender vetlogMailSender = mock(JavaMailSender.class);
  private final Template freeMarkerTemplate = mock(Template.class);
  private final Map<String, Client> templateStrategy = new HashMap<>();
  private final Map<String, String> values =
      Map.of("email", "contact@josdem.io", "subject", "Hello from Jmailer!");

  @BeforeEach
  void setup() {
    templateStrategy.put(DEFAULT_TEMPLATE, new Client(javaMailSender, "Hello from Jmailer!"));
    templateStrategy.put(VETLOG_TEMPLATE, new Client(vetlogMailSender, "Hello from Vetlog!"));
    mailService = new MailServiceImpl(configuration, templateStrategy);
  }

  @Test
  @DisplayName("sending mail with default template")
  void shouldSendMailWithDefaultTemplate() throws IOException {
    given(configuration.getTemplate(DEFAULT_TEMPLATE)).willReturn(freeMarkerTemplate);
    mailService.sendMailWithTemplate(values, model, DEFAULT_TEMPLATE);
    verify(javaMailSender).send(isA(MimeMessagePreparator.class));
  }

  @Test
  @DisplayName("sending mail with vetlog template")
  void shouldSendMailWithVetlogTemplate() throws IOException {
    given(configuration.getTemplate(VETLOG_TEMPLATE)).willReturn(freeMarkerTemplate);
    mailService.sendMailWithTemplate(values, model, VETLOG_TEMPLATE);
    verify(vetlogMailSender).send(isA(MimeMessagePreparator.class));
  }
}
