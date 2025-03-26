/*
 Copyright 2025 Jose Morales contact@josdem.io

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

package com.josdem.jmailer.config;

import jakarta.jms.ConnectionFactory;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final EmailProperties emailProperties;

  @Bean
  JavaMailSenderImpl defaultMailSender() throws MessagingException {
    return getMailSenderConfig(emailProperties.getUsername(), emailProperties.getPassword());
  }

  @Bean
  JavaMailSenderImpl vetlogMailSender() throws MessagingException {
    return getMailSenderConfig(
        emailProperties.getVetlogUsername(), emailProperties.getVetlogPassword());
  }

  private JavaMailSenderImpl getMailSenderConfig(String username, String password) throws MessagingException {
    var mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    var prop = mailSender.getJavaMailProperties();
    prop.put("mail.imap.ssl.enable", "true");
    prop.put("mail.imap.auth.mechanisms", "XOAUTH2");
    Session session = Session.getInstance(prop);
    Store store = session.getStore("imap");
    store.connect("imap.gmail.com", username, "oauth2_access_token");
    return mailSender;
  }

  @Bean
  public JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
    var factory = new SimpleJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    return factory;
  }
}
