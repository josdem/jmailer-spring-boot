package com.jos.dem.jmailer

import spock.lang.Specification

import com.jos.dem.jmailer.controller.EmailerController
import com.jos.dem.jmailer.service.EmailerFormatter
import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.Command
import com.jos.dem.jmailer.command.MessageCommand

class EmailerControllerSpec extends Specification{

  EmailerController controller = new EmailerController()

  EmailerFormatter emailerFormatter = Mock(EmailerFormatter)
  EmailerService emailerService = Mock(EmailerService)

  def setup(){
    controller.emailerFormatter = emailerFormatter
    controller.emailerService = emailerService
  }

  void "should send a message from a form"(){
    given:"A messageCommand"
      Command command = new MessageCommand(email:'josdem@email.com')
    when:"We send message"
      emailerFormatter.format(command) >> command
      controller.form(command)
    then:"We expect message sent"
    1 * emailerService.sendEmail(command)
  }

}
