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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
class TemplateConfigTest {

    private TemplateConfig templateConfig;

    @Mock
    private JavaMailSender defaultMailSender;

    @Mock
    private JavaMailSender vetlogMailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        templateConfig = new TemplateConfig(defaultMailSender, vetlogMailSender);
    }

    @Test
    @DisplayName("getting a template strategies")
    void shouldGetTemplateStrategies(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        var templateStrategy = templateConfig.templateStrategy();
        assertNotNull(templateStrategy);
        assertEquals(8, templateStrategy.size());
        assertTrue(templateStrategy.containsKey("welcome.ftl"));
        assertTrue(templateStrategy.containsKey("welcome_es.ftl"));
        assertTrue(templateStrategy.containsKey("adoption.ftl"));
        assertTrue(templateStrategy.containsKey("adoption_es.ftl"));
        assertTrue(templateStrategy.containsKey("forgotPassword.ftl"));
        assertTrue(templateStrategy.containsKey("forgotPassword_es.ftl"));
        assertTrue(templateStrategy.containsKey("message.ftl"));
        assertTrue(templateStrategy.containsKey("register.ftl"));
    }

    @Test
    @DisplayName("getting a default client")
    void shouldGetDefaultClient(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        var templateStrategy = templateConfig.templateStrategy();
        var defaultClient = templateStrategy.get("message.ftl");
        assertNotNull(defaultClient);
    }

    @Test
    @DisplayName("getting a vetlog client")
    void shouldGetVetlogClient(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        var templateStrategy = templateConfig.templateStrategy();
        var vetlogClient = templateStrategy.get("welcome.ftl");
        assertNotNull(vetlogClient);
    }
}