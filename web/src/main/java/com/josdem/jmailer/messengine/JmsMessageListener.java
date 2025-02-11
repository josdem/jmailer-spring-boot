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

package com.josdem.jmailer.messengine;

import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.service.NotificationService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JmsMessageListener {

  private final NotificationService notificationService;

  @JmsListener(destination = "destination", containerFactory = "myJmsContainerFactory")
  public void receiveMessage(Message message) throws JMSException {
    var command = ((ObjectMessage) message).getObject();
    log.info("Message received");
    notificationService.sendNotification((MessageCommand) command);
  }
}
