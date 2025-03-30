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

package com.josdem.jmailer.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josdem.jmailer.command.MessageCommand;
import com.josdem.jmailer.service.MailService;
import com.josdem.jmailer.service.NotificationService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final MailService mailService;
  private final ObjectMapper mapper;

  @Override
  public void sendNotification(MessageCommand command) {
    var data = Map.of("email", command.getEmail());
    var model = mapper.convertValue(command, new TypeReference<Map<String, String>>() {});
    mailService.sendMailWithTemplate(data, model, command.getTemplate());
  }
}
