package com.jos.dem.jmailer.messengine

import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.ObjectMessage

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.NotificationService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class JmsMessageListener implements MessageListener {

  @Autowired
  NotificationService notificationService

  Log log = LogFactory.getLog(getClass())

  void onMessage(Message message) {
    log.info 'Email message received'

    Object command =  ((ObjectMessage) message).getObject()
    notificationService.sendNotification(command)
  }

}
