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

package com.josdem.jmailer.service

import com.josdem.jmailer.command.MessageCommand
import com.josdem.jmailer.config.EmailProperties
import com.josdem.jmailer.exception.BusinessException
import com.josdem.jmailer.service.impl.EmailValidatorServiceImpl
import com.josdem.jmailer.service.impl.FilterServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory

internal class EmailValidatorServiceTest {
    private lateinit var emailValidatorService: EmailValidatorService

    private val emailProperties: EmailProperties = EmailProperties()

    private val spamTokens = listOf("spam", "offer", "discount")
    private val spamNames = listOf("John", "Edward", "Sebastian")

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        emailProperties.spamTokens = spamTokens
        emailProperties.spamNames = spamNames
        emailProperties.factor = 0.5
        val filterService = FilterServiceImpl(emailProperties)
        emailValidatorService = EmailValidatorServiceImpl(emailProperties, filterService)
    }

    @Test
    fun `should validate spam by message`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        var command = getCommand()
        command.message = "discount"

        assertThrows<BusinessException> {
            emailValidatorService.validate(command)
        }
    }

    @Test
    fun `should validate spam by name`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        var command = getCommand()
        command.name = "John"

        assertThrows<BusinessException> {
            emailValidatorService.validate(command)
        }
    }

    @Test
    fun `should validate spam by keyword`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        var command = getCommand()
        command.name = "NHUQfuLarRMDj"

        assertThrows<BusinessException> {
            emailValidatorService.validate(command)
        }
    }

    @Test
    fun `should validate adoption by message`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        var command = getCommand()
        command.message = "5610184061"
        emailValidatorService.validate(command)
    }

    private fun getCommand(): MessageCommand {
        var command =
            MessageCommand().apply {
                name = "josdem"
                token = "userToken"
                template = "message.ftl"
                email = "contact@josdem.io"
                redirect = "redirect"
                subject = "Hello"
                message = "Hello from Junit5!"
                source = "source"
            }
        return command
    }
}
