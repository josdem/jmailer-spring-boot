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

package com.josdem.jmailer.service.impl;

import com.josdem.jmailer.command.Command;
import com.josdem.jmailer.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableJms
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final JmsTemplate jmsTemplate;

  public void message(final Command command) {
    MessageCreator messageCreator =
        session -> {
          var message = session.createObjectMessage();
          message.setObject(command);
          return message;
        };

    log.info("Sending a new message");
    jmsTemplate.send("destination", messageCreator);
  }
}
