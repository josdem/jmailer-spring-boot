package com.jos.dem.jmailer.service.impl

import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.service.EmailerFormatter

class EmailerFormatter implements EmailerFormatter{

  MessageCommand format(MessageCommand command){
    if(command.source?.equals('josdem.io')){
      command.email = 'joseluis.delacruz@gmail.com'
      command.message = "${command.message} Reply to: ${command.emailContact}, source: ${command.source}"
      return command
    }
    command.message = "${command.message}, Thank you for using Jmailer!"
    return command
  }

}
