package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.command.Command;
import com.jos.dem.jmailer.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MessageServiceTest {

  private MessageService messageService;

  private JmsTemplate jmsTemplate = mock(JmsTemplate.class);

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    messageService = new MessageServiceImpl(jmsTemplate);
  }

  @Test
  @DisplayName("sending a message")
  void shouldSendMessage() {
    Command command = mock(Command.class);
    messageService.message(command);
    verify(jmsTemplate).send(isA(String.class), isA(MessageCreator.class));
  }
}
