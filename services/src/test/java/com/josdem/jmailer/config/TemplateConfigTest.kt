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
}
