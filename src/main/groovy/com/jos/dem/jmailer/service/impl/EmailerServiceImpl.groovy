package com.jos.dem.jmailer.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.MessageCommand

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class EmailerServiceImpl implements EmailService {

  @Autowired
  MessageService messageService

  Log log = LogFactory.getLog(this.class)

  def sendEmail(MessageCommand command){
    log.info 'Sending email ${command.email}'
    messageService.message(command)
  }

}
