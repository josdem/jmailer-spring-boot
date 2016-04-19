/*
  Copyright 2016 José Luis De la Cruz Morales joseluis.delacruz@gmail.com

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.jos.dem.jmailer

import javax.jms.ConnectionFactory

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.config.SimpleJmsListenerContainerFactory
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.util.FileSystemUtils
import org.springframework.boot.context.web.SpringBootServletInitializer

@SpringBootApplication
class JmailerApplication extends SpringBootServletInitializer {

  @Value('${email.username}')
  String username
  @Value('${email.password}')
  String password

  @Bean
  JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
    SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory()
    factory.setConnectionFactory(connectionFactory)
    return factory
  }

  @Bean
  JavaMailSenderImpl javaMailSenderImpl(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl()
    mailSender.setHost("smtp.gmail.com")
    mailSender.setPort(587)
    mailSender.setUsername(username)
    mailSender.setPassword(password)
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
