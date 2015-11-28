package com.jos.dem.jmailer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.util.FileSystemUtils

@Component
public class Receiver {

  @Autowired
  ConfigurableApplicationContext context

  @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">")
    context.close()
    FileSystemUtils.deleteRecursively(new File("activemq-data"))
  }
}
