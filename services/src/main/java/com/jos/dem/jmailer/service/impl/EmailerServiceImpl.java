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

import com.jos.dem.jmailer.collaborator.CommandValidator;
import com.jos.dem.jmailer.command.Command;
import com.jos.dem.jmailer.exception.BusinessException;
import com.jos.dem.jmailer.service.EmailerService;
import com.jos.dem.jmailer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailerServiceImpl implements EmailerService {

  @Autowired private MessageService messageService;
  @Autowired private CommandValidator validator;

  public void sendEmail(Command command) {
    if (!validator.isValid(command)) {
      throw new BusinessException("Parameters do not fit the constraints");
    }
    messageService.message(command);
  }
}
