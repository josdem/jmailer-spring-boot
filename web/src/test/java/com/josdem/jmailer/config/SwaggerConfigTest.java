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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SwaggerConfigTest {
    private static final String SWAGGER_SERVER = "http://localhost:8080";
    private SwaggerConfig swaggerConfig;

    @Mock
    private ApplicationProperties applicationProperties;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        swaggerConfig = new SwaggerConfig(SWAGGER_SERVER, applicationProperties);
    }

    @Test
    @DisplayName("adds Swagger service")
    void shouldAddSwaggerService(TestInfo testInfo) {
        log.info("Running: {}", testInfo.getDisplayName());
        assertEquals(
                1, swaggerConfig.springShopOpenAPI().getServers().size(), "should have one server");
        assertEquals(
                SWAGGER_SERVER,
                swaggerConfig.springShopOpenAPI().getServers().get(0).getUrl(),
                "should have expected server");
    }
}
