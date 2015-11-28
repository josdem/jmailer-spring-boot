package com.jos.dem.jmailer

import javax.jms.ConnectionFactory

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.config.SimpleJmsListenerContainerFactory
import org.springframework.util.FileSystemUtils

@SpringBootApplication
class JmailerApplication {

  @Bean
  JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory()
    factory.setConnectionFactory(connectionFactory)
    return factory
  }

  @Bean
  public JavaMailSenderImpl javaMailSenderImpl(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl()
    mailSender.setHost("smtp.gmail.com")
    mailSender.setPort(587)
    mailSender.setUsername("soporte@techminds.com.mx")
    mailSender.setPassword("T3chminds2016")
    Properties prop = mailSender.getJavaMailProperties()
    prop.put("mail.transport.protocol", "smtp")
    prop.put("mail.smtp.auth", "true")
    prop.put("mail.smtp.starttls.enable", "true")
    prop.put("mail.debug", "true")
    return mailSender
  }

  static void main(String[] args) {
    SpringApplication.run(JmailerApplication.class, args)
  }

}
