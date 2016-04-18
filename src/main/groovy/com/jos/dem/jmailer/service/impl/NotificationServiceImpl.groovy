package com.jos.dem.jmailer.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.jos.dem.jmailer.service.MailService
import com.jos.dem.jmailer.service.NotificationService
import com.jos.dem.jmailer.command.MessageCommand
import org.springframework.beans.factory.annotation.Value

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class NotificationServiceImpl implements NotificationService {

  @Autowired
  MailService mailService

  @Value('${email.template}')
  String template
  @Value('${email.subject}')
  String subject

  private Log log = LogFactory.getLog(getClass())

  @Override
  Boolean sendNotification(MessageCommand messageCommand) {
     def data = [email:messageCommand.email, subject:subject]
     mailService.sendMailWithTemplate(data, messageCommand.properties, template)
  }

}
