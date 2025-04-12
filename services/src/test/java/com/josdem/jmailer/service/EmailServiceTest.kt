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

import com.josdem.jmailer.command.Command
import com.josdem.jmailer.service.impl.EmailerServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory

internal class EmailServiceTest {
    private lateinit var emailService: EmailerServiceImpl

    @Mock private lateinit var messageService: MessageService

    @Mock private lateinit var emailValidatorService: EmailValidatorService

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        emailService = EmailerServiceImpl(messageService, emailValidatorService)
    }

    @Test
    fun `should send a message`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val command = mock(Command::class.java)
        emailService.sendEmail(command)
        verify(messageService).message(command)
        verify(emailValidatorService).validate(command)
    }
}
