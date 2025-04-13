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
import com.josdem.jmailer.service.impl.MessageServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.mockito.Mock
import org.mockito.Mockito.isA
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

internal class MessageServiceTest {
    private lateinit var messageService: MessageService

    @Mock private lateinit var jmsTemplate: JmsTemplate

    private val messageCommand = MessageCommand()

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        messageService = MessageServiceImpl(jmsTemplate)
    }

    @Test
    fun `should send a message`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        messageService.message(messageCommand)
        verify(jmsTemplate).send(isA(String::class.java), isA(MessageCreator::class.java))
    }
}
