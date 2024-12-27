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
import com.josdem.jmailer.service.EmailValidatorService;
import com.josdem.jmailer.service.EmailerService;
import com.josdem.jmailer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailerServiceImpl implements EmailerService {

  private final MessageService messageService;
  private final EmailValidatorService validatorService;

  public void sendEmail(Command command) {
    validatorService.validate(command);
    messageService.message(command);
  }
}
