package com.jos.dem.jmailer.service.impl

import javax.jms.JMSException
import javax.jms.ObjectMessage
import javax.jms.Message
import javax.jms.Session

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.command.MessageCommand

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
@EnableJms
class MessageServiceImpl implements MessageService {

  @Autowired
  JmsTemplate jmsTemplate

  Log log = LogFactory.getLog(this.class)

  void message(final MessageCommand command) {
    MessageCreator messageCreator = new MessageCreator() {

      @Override
      public Message createMessage(Session session) throws JMSException {
        ObjectMessage message = session.createObjectMessage()
        message.setObject(command)
        return message
      }
    }

    log.info 'Sending a new message'
    jmsTemplate.send("destination", messageCreator)
  }
}
