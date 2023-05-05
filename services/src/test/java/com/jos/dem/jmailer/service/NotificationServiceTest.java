package com.jos.dem.jmailer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationServiceTest {
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
    Map data = Map.of("email", "contact@josdem.io", "subject", "Hello from Jmailer!");
    MessageCommand messageCommand = new MessageCommand();
    messageCommand.setEmail("contact@josdem.io");
    messageCommand.setTemplate("message.ftl");
    ReflectionTestUtils.setField(notificationService, "subject", "Hello from Jmailer!");
    when(mapper.convertValue(messageCommand, Map.class)).thenReturn(model);
    notificationService.sendNotification(messageCommand);
    verify(mailService).sendMailWithTemplate(data, model, "message.ftl");
  }
}
