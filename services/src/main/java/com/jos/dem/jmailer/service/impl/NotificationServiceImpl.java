/*
  Copyright 2021 Jose Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jos.dem.jmailer.command.MessageCommand;
import com.jos.dem.jmailer.service.MailService;
import com.jos.dem.jmailer.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired private MailService mailService;

  @Value("${email.subject}")
  private String subject;

  @Autowired private ObjectMapper mapper;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void sendNotification(MessageCommand command) {
    Map data = Map.of("email", command.getEmail(), "subject", subject);
    mailService.sendMailWithTemplate(
        data, mapper.convertValue(command, Map.class), command.getTemplate());
  }
}
