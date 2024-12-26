package com.josdem.jmailer.service;

import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.service.impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Slf4j
class MessageServiceTest {

  private MessageService messageService;

  private final JmsTemplate jmsTemplate = mock(JmsTemplate.class);
  private final MessageCommand command = new MessageCommand();

  @BeforeEach
  void setup() {
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
