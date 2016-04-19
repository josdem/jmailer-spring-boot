package com.jos.dem.jmailer.messengine

import javax.jms.Message
import javax.jms.ObjectMessage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.util.FileSystemUtils

import com.jos.dem.jmailer.service.NotificationService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Component
class JmsMessageListener {

  @Autowired
  NotificationService notificationService

  Log log = LogFactory.getLog(this.class)

  @JmsListener(destination = "destination", containerFactory = "myJmsContainerFactory")
  public void receiveMessage(Message message) {
    Object command =  ((ObjectMessage) message).getObject()
    log.info "Received ${command.dump()}"
    notificationService.sendNotification(command)
  }

}

