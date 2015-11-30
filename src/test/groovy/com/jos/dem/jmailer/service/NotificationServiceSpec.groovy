package com.jos.dem.jmailer.service

import spock.lang.Specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.IntegrationTest
import org.springframework.test.context.ContextConfiguration

import com.jos.dem.jmailer.JmailerApplication
import com.jos.dem.jmailer.command.MessageCommand

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = JmailerApplication.class)
@IntegrationTest
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
