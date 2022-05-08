/*
  Copyright 2021 Jose Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jos.dem.jmailer.service.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

  @Autowired
  private Configuration configuration;

  @Autowired
  private JavaMailSender javaMailSender;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public void sendMailWithTemplate(Map<String,String> values, Map model, String template) {

    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        Template myTemplate = configuration.getTemplate(template);
        message.setTo(values.get("email"));
        message.setSubject(values.get("subject"));
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(myTemplate, model);
        message.setText(text, true);
      }
    };

    this.javaMailSender.send(preparator);
  }

}