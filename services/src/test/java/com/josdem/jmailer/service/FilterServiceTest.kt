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

import com.josdem.jmailer.config.EmailProperties
import com.josdem.jmailer.service.impl.FilterServiceImpl
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class FilterServiceTest {
    private lateinit var filterService: FilterService

    @BeforeEach
    fun setUp() {
        val emailProperties = EmailProperties()
        emailProperties.factor = 0.5
        filterService = FilterServiceImpl(emailProperties)
    }

    @ValueSource(strings = ["NHUQfuLarRMDj", "rJVyFMNsmXhPUvG", "rVhBLNPSNIPE", "SxeQsgXI", "NDDmMAUftYXkxO"])
    @ParameterizedTest
    fun `should filter by user`(keyword: String) {
        assertFalse(filterService.isValidUser(keyword))
    }

    @ValueSource(strings = ["josdem", "johndoe"])
    @ParameterizedTest
    fun `should validate valid user`(keyword: String) {
        assertTrue(filterService.isValidUser(keyword))
    }
}
