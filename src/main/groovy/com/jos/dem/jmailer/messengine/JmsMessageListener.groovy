package com.jos.dem.jmailer.messengine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.util.FileSystemUtils

@Component
class JmsMessageListener {

  @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">")
  }

}

