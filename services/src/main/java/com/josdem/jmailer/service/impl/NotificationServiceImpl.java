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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.service.MailService;
import com.josdem.jmailer.service.NotificationService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

  private final MailService mailService;
  private final ObjectMapper mapper;
  private String subject;

  public NotificationServiceImpl(
      MailService mailService, ObjectMapper mapper, @Value("${email.subject}") String subject) {
    this.mailService = mailService;
    this.mapper = mapper;
    this.subject = subject;
  }

  @Override
  public void sendNotification(MessageCommand command) {
    Map data = Map.of("email", command.getEmail(), "subject", subject);
    mailService.sendMailWithTemplate(
        data, mapper.convertValue(command, Map.class), command.getTemplate());
  }
}
