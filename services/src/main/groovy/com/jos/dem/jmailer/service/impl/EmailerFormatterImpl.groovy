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
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.command.Command
import com.jos.dem.jmailer.command.PostCommand
import com.jos.dem.jmailer.service.EmailerFormatter
import com.jos.dem.jmailer.service.LocaleService
import com.jos.dem.jmailer.enums.MessageType

@Service
class EmailerFormatterImpl implements EmailerFormatter{

  @Autowired
  LocaleService localeService

  Command format(PostCommand command){
    if(command.type && MessageType."${command.type}".equals(MessageType.REGISTER)){
      command.message = "${command.message} Reply to: ${command.emailContact}, source: ${command.source}"
      return command
    }
    command.message = "${command.message}, ${localeService.getMessage('thank.you')}"
    return command
  }

}
