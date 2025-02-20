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

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TemplateConfig {

    private final JavaMailSender defaultMailSender;
    private final JavaMailSender vetlogMailSender;

    public Map<String, JavaMailSender> templateStrategy() {
        Map<String, JavaMailSender> templateStrategy = new HashMap<>();
        templateStrategy.put("welcome.ftl", vetlogMailSender);
        templateStrategy.put("adoption.ftl", vetlogMailSender);
        templateStrategy.put("forgotPassword.ftl", vetlogMailSender);
        templateStrategy.put("message.ftl", defaultMailSender);
        templateStrategy.put("register.ftl", defaultMailSender);
        templateStrategy.put("third_party.ftl", defaultMailSender);
        return templateStrategy;
    }
}
