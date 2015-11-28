package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.MessageCommand

interface MessageService {

  void message(final MessageCommand command)

}
