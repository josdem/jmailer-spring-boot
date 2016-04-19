package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.MessageCommand

interface EmailerService {

  def sendEmail(MessageCommand command)

}
