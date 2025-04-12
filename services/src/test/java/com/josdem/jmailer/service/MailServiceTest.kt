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
import org.mockito.Mockito.isA
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessagePreparator

private const val DEFAULT_TEMPLATE: String = "message.ftl"
private const val VETLOG_TEMPLATE: String = "welcome.ftl"

internal class MailServiceTest {
    private lateinit var mailService: MailService

    @Mock private lateinit var configuration: Configuration

    @Mock private lateinit var javaMailSender: JavaMailSender

    @Mock private lateinit var vetlogMailSender: JavaMailSender

    private val templateStrategy = mutableMapOf<String, Client>()

    private val model = mutableMapOf<String, String>()

    private val values = mapOf("email" to "contact@josdem.io", "subject" to "Hello from Jmailer!")

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        templateStrategy[DEFAULT_TEMPLATE] = Client(javaMailSender)
        templateStrategy[VETLOG_TEMPLATE] = Client(vetlogMailSender)
        mailService = MailServiceImpl(configuration, templateStrategy)
    }

    @Test
    fun `should throw exception when template is not found`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val templateName = "nonexistent.ftl"
        assertThrows<BusinessException> { mailService.sendMailWithTemplate(values, model, templateName) }
    }

    @Test
    fun `should send an email with default template`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mailService.sendMailWithTemplate(values, model, DEFAULT_TEMPLATE)
        verify(javaMailSender).send(isA(MimeMessagePreparator::class.java))
    }

    @Test
    fun `should send an email with vetlog template`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mailService.sendMailWithTemplate(values, model, VETLOG_TEMPLATE)
        verify(vetlogMailSender).send(isA(MimeMessagePreparator::class.java))
    }
}
