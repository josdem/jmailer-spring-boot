package com.jos.dem.jmailer.service.impl

import org.springframework.stereotype.Service

import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.service.EmailerFormatter
import com.jos.dem.jmailer.enums.MessageType

@Service
class EmailerFormatterImpl implements EmailerFormatter{

  MessageCommand format(MessageCommand command){
    if(command.type && MessageType."${command.type}".equals(MessageType.REGISTER)){
      command.email = 'joseluis.delacruz@gmail.com'
      command.message = "${command.message} Reply to: ${command.emailContact}, source: ${command.source}"
      return command
    }
    command.message = "${command.message}, Thank you for using Jmailer!"
    return command
  }

}
