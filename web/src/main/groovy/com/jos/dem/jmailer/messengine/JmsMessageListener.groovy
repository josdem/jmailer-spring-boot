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

package com.jos.dem.jmailer.messengine

import javax.jms.Message
import javax.jms.ObjectMessage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.util.FileSystemUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.jos.dem.jmailer.service.NotificationService
import com.jos.dem.jmailer.command.MessageCommand

@Component
class JmsMessageListener {

  @Autowired
  NotificationService notificationService

  Logger log = LoggerFactory.getLogger(this.class)

  @JmsListener(destination = "destination", containerFactory = "myJmsContainerFactory")
  public void receiveMessage(Message message) {
    Object command =  ((ObjectMessage) message).getObject()
    log.info "Received ${command.dump()}"
    notificationService.sendNotification(command)
  }

}

