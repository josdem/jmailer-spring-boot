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

import com.josdem.jmailer.model.Client;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@RequiredArgsConstructor
public class TemplateConfig {

  private final JavaMailSender defaultMailSender;
  private final JavaMailSender vetlogMailSender;
  private final EmailProperties emailProperties;

  @Bean
  public Map<String, Client> templateStrategy() {
    var vetlogClient =
        new Client(
            vetlogMailSender,
            emailProperties.getVetlogSubject(),
            emailProperties.getVetlogSubjectSpanish());
    var jmailerClient =
        new Client(
            defaultMailSender,
            emailProperties.getJmailerSubject(),
            emailProperties.getVetlogSubjectSpanish());

    Map<String, Client> templateStrategy = new HashMap<>();
    templateStrategy.put("welcome.ftl", vetlogClient);
    templateStrategy.put("adoption.ftl", vetlogClient);
    templateStrategy.put("forgotPassword.ftl", vetlogClient);
    templateStrategy.put("message.ftl", jmailerClient);
    templateStrategy.put("register.ftl", jmailerClient);
    return templateStrategy;
  }
}
