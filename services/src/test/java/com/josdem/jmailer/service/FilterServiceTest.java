/*
  Copyright 2024 Jose Morales joseluis.delacruz@gmail.com

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

package com.josdem.jmailer.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.josdem.jmailer.config.EmailProperties;
import com.josdem.jmailer.service.impl.FilterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
class FilterServiceTest {

  private FilterService filterService;

  @BeforeEach
  void setup() {
    var emailProperties = new EmailProperties();
    emailProperties.setFactor(0.5);
    filterService = new FilterServiceImpl(emailProperties);
  }

  @ValueSource(
      strings = {"NHUQfuLarRMDj", "rJVyFMNsmXhPUvG", "rVhBLNPSNIPE", "SxeQsgXI", "NDDmMAUftYXkxO"})
  @ParameterizedTest
  @DisplayName("filtering user")
  void shouldFilterUser(String keyword) {
    assertFalse(filterService.isValidUser(keyword));
  }

  @ValueSource(strings = {"josdem", "johndoe"})
  @ParameterizedTest
  @DisplayName("accepting user")
  void shouldAcceptUser(String keyword) {
    assertTrue(filterService.isValidUser(keyword));
  }
}
