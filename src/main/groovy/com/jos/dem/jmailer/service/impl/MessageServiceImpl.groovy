package com.jos.dem.jmailer.service.impl

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.ObjectMessage
import javax.jms.Session
import javax.jms.Destination

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Service

import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.command.MessageCommand

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class MessageServiceImpl implements MessageService {

  @Autowired
  JmsTemplate template
  @Autowired
  Destination destination

  Log log = LogFactory.getLog(getClass())

  void message(final MessageCommand command) {
    log.info "CALLING Message"

    template.send(destination, new MessageCreator() {
      Message createMessage(Session session) throws JMSException {
        ObjectMessage message = session.createObjectMessage()
        message.setObject(command)
        return message
      }
    })
  }
}
