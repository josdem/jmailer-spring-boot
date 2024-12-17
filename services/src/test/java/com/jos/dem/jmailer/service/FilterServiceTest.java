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

package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.service.impl.FilterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
class FilterServiceTest {

    private final FilterService filterService = new FilterServiceImpl();

    @Test
    @DisplayName("filtering user")
    void shouldFilterUser(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        var keyword = "NHUQfuLarRMDj";
        assertFalse(filterService.isValidUser(keyword));
    }
}
