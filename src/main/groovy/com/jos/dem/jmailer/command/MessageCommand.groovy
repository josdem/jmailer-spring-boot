package com.jos.dem.jmailer.command

import com.jos.dem.jmailer.enums.MessageType

class MessageCommand implements Command {
  String email
  String message
  String name
  String emailContact
  String source
  MessageType type
}

