package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.service.impl.MailServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MailServiceTest {
  private MailService mailService;
  private Configuration configuration = mock(Configuration.class);
  private JavaMailSender javaMailSender = mock(JavaMailSender.class);

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mailService = new MailServiceImpl(configuration, javaMailSender);
  }

  @Test
  @DisplayName("sending mail with template")
  void shouldSendMailWithTemplate() throws IOException {
    Map<String, String> values =
        Map.of("email", "contact@josdem.io", "subject", "Hello from Jmailer!");
    Map model = mock(Map.class);
    String template = "message.ftl";
    Template freeMarkerTemplate = mock(Template.class);
    mailService.sendMailWithTemplate(values, model, template);
    when(configuration.getTemplate(template)).thenReturn(freeMarkerTemplate);
    verify(javaMailSender).send(isA(MimeMessagePreparator.class));
  }
}
