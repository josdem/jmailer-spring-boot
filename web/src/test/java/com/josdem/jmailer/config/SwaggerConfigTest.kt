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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory

private const val SWAGGER_SERVER: kotlin.String = "http://localhost:8080"

internal class SwaggerConfigTest {
    private lateinit var swaggerConfig: SwaggerConfig

    @Mock private lateinit var applicationProperties: ApplicationProperties

    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        swaggerConfig = SwaggerConfig(SWAGGER_SERVER, applicationProperties)
    }

    @Test
    fun `should add swagger service`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        assertEquals(
            1,
            swaggerConfig
                .springShopOpenAPI()
                .servers
                .size,
            "should have one server",
        )
        assertEquals(
            SWAGGER_SERVER,
            swaggerConfig
                .springShopOpenAPI()
                .servers
                .first()
                .url,
            "should have correct server",
        )
    }
}
