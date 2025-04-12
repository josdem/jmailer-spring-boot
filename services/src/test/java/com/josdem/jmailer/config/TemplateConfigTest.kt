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

package com.josdem.jmailer.config

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender

internal class TemplateConfigTest {
    private lateinit var templateConfig: TemplateConfig

    @Mock
    private lateinit var defaultMailSender: JavaMailSender

    @Mock
    private lateinit var vetlogMailSender: JavaMailSender

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        templateConfig = TemplateConfig(defaultMailSender, vetlogMailSender)
    }

    @Test
    fun `getting template strategies`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        var templateStrategy = templateConfig.templateStrategy()
        assertNotNull(templateStrategy)
        assertEquals(8, templateStrategy.size)
        assertTrue(templateStrategy.containsKey("welcome.ftl"))
        assertTrue(templateStrategy.containsKey("welcome_es.ftl"))
        assertTrue(templateStrategy.containsKey("adoption.ftl"))
        assertTrue(templateStrategy.containsKey("adoption_es.ftl"))
        assertTrue(templateStrategy.containsKey("forgotPassword.ftl"))
        assertTrue(templateStrategy.containsKey("forgotPassword_es.ftl"))
        assertTrue(templateStrategy.containsKey("message.ftl"))
        assertTrue(templateStrategy.containsKey("register.ftl"))
    }

    @Test
    fun `getting default client`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val templateStrategy = templateConfig.templateStrategy()
        val defaultClient = templateStrategy["message.ftl"]
        assertNotNull(defaultClient)
    }

    @Test
    fun `getting vetlog client`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val templateStrategy = templateConfig.templateStrategy()
        val vetlogClient = templateStrategy["welcome.ftl"]
        assertNotNull(vetlogClient)
    }
}
