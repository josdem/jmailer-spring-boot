package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.command.FormCommand;
import com.jos.dem.jmailer.service.impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Slf4j
class MessageServiceTest {

  private MessageService messageService;

  private JmsTemplate jmsTemplate = mock(JmsTemplate.class);
  private final FormCommand command = new FormCommand("redirect", "source");

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    messageService = new MessageServiceImpl(jmsTemplate);
  }

  @Test
  @DisplayName("sending a message")
  void shouldSendMessage(TestInfo testInfo) {
    log.info("Running: {} with command: {}", testInfo.getDisplayName(), command);
    messageService.message(command);
    verify(jmsTemplate).send(isA(String.class), isA(MessageCreator.class));
  }
}
