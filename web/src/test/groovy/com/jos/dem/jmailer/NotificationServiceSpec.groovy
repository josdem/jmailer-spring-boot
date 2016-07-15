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

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.IntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

import com.jos.dem.jmailer.JmailerApplication
import com.jos.dem.jmailer.service.NotificationService
import com.jos.dem.jmailer.command.MessageCommand

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = JmailerApplication.class)
@IntegrationTest
@WebAppConfiguration
class NotificationServiceSpec extends Specification {

  @Autowired
  NotificationService notificationService

  String email = 'joseluis.delacruz@gmail.com'

  void "should send an email"(){
  given:"A MessageCommand"
    def messageCommand = new MessageCommand(email:email, message:'Hello from spock')
  when:"We send notification"
    def result = notificationService.sendNotification(messageCommand)
  then:"We expect email sent"
    result
  }

}
