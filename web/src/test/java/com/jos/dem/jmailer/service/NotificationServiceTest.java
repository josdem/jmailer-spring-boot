package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.command.MessageCommand;
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
class NotificationServiceTest {

  private final NotificationService notificationService;

  @DisplayName("send an email")
  @Test
  void shouldSendEmail(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    MessageCommand message = new MessageCommand();
    message.setEmail("joseluis.delacruz@gmail.com");
    message.setMessage("Hello from Junit5!");
    message.setName("Junit Jupiter");
    message.setTemplate("message.ftl");
    message.setToken("userToken");
    notificationService.sendNotification(message);
  }
}
