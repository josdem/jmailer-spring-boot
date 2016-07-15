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

package com.jos.dem.jmailer

import spock.lang.Specification

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.service.impl.EmailerServiceImpl
import com.jos.dem.jmailer.command.MessageCommand

class EmailerServiceSpec extends Specification {

  EmailerService service = new EmailerServiceImpl()

  MessageService messageService = Mock(MessageService)

  def setup(){
    service.messageService = messageService
  }

  void "should send a message"(){
  given:"A command"
    MessageCommand command = new MessageCommand(
      email:'josdem@email.com',
      message:'Hello',
      name:'josdem'
    )
  when:"We send email"
    service.sendEmail(command)
  then:"We expect send message"
  1 * messageService.message(command)
  }

}