package com.jos.dem.jmailer.service

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
