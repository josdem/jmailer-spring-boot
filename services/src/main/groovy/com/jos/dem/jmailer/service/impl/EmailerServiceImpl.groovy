/*
  Copyright 2016 José Luis De la Cruz Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.command.Command
import com.jos.dem.jmailer.exception.EmailerException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class EmailerServiceImpl implements EmailerService {

  @Autowired
  MessageService messageService

  Log log = LogFactory.getLog(this.class)

  def sendEmail(Command command){
    log.info 'Sending email ${command.email}'
    messageService.message(command)
  }

}
