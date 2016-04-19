package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.MessageCommand

interface EmailerFormatter {

  MessageCommand format(MessageCommand command)

}

