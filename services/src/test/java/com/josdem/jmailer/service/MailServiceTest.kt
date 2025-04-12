package com.josdem.jmailer.service

import com.josdem.jmailer.exception.BusinessException
import com.josdem.jmailer.model.Client
import com.josdem.jmailer.service.impl.MailServiceImpl
import freemarker.template.Configuration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory

internal class MailServiceTest {
    private lateinit var mailService: MailService

    @Mock private lateinit var configuration: Configuration

    private val templateStrategy: Map<String, Client> = emptyMap()

    private val model: Map<String, String> = emptyMap()

    private val values = mapOf("email" to "contact@josdem.io", "subject" to "Hello from Jmailer!")

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mailService = MailServiceImpl(configuration, templateStrategy)
    }

    @Test
    fun `should throw exception when template is not found`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val templateName = "nonexistent.ftl"
        assertThrows<BusinessException> { mailService.sendMailWithTemplate(values, model, templateName) }
    }
}
