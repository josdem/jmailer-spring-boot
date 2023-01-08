/*
  Copyright 2016 Jose Morales joseluis.delacruz@gmail.com

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

import com.jos.dem.jmailer.controller.EmailerController
import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.Command
import com.jos.dem.jmailer.command.MessageCommand

class EmailerControllerSpec extends Specification{

  EmailerController controller = new EmailerController()

  EmailerService emailerService = Mock(EmailerService)

  def setup(){
    controller.emailerService = emailerService
  }

  void "should send a message from a form"(){
    given:"A messageCommand"
      Command command = new MessageCommand(email:'josdem@email.com')
    when:"We send message"
      controller.form(command)
    then:"We expect message sent"
    1 * emailerService.sendEmail(command)
  }

}
