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

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.josdem.jmailer.command.MessageCommand
import com.josdem.jmailer.service.impl.NotificationServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.mockito.ArgumentMatchers.isA
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory

private const val SENDER_EMAIL = "contact@josdem.io"
private const val TARGET_EMAIL = "joseluis.delacruz@gmail.com"
private const val TEMPLATE = "message.ftl"

internal class NotificationServiceTest {
    private lateinit var notificationService: NotificationServiceImpl

    @Mock private lateinit var mailService: MailService

    @Mock private lateinit var objectMapper: ObjectMapper

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        notificationService = NotificationServiceImpl(mailService, objectMapper)
    }

    @Test
    fun `should send a notification`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        val model = mapOf<String, String>("email" to SENDER_EMAIL)
        val data = mapOf<String, String>("email" to TARGET_EMAIL)
        val command = mock(MessageCommand::class.java)

        `when`(command.email).thenReturn(TARGET_EMAIL)
        `when`(command.template).thenReturn(TEMPLATE)
        `when`(objectMapper.convertValue(isA(MessageCommand::class.java), isA(TypeReference::class.java))).thenReturn(model)

        notificationService.sendNotification(command)

        verify(mailService).sendMailWithTemplate(data, model, TEMPLATE)
    }
}
