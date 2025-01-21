/*
  Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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

package com.josdem.jmailer.service.impl;

import com.josdem.jmailer.service.MailService;
import freemarker.template.Configuration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final Configuration configuration;
  private final JavaMailSender javaMailSender;

  public void sendMailWithTemplate(Map<String, String> values, Map model, String template) {

    MimeMessagePreparator mailer =
        mimeMessage -> {
          var message = new MimeMessageHelper(mimeMessage, true);
          var myTemplate = configuration.getTemplate(template);
          message.setTo(values.get("email"));
          message.setSubject(values.get("subject"));
          var text = FreeMarkerTemplateUtils.processTemplateIntoString(myTemplate, model);
          message.setText(text, true);
        };

    javaMailSender.send(mailer);
  }
}
