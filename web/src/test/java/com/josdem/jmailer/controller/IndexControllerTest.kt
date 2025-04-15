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

package com.josdem.jmailer.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@SpringBootTest
@AutoConfigureMockMvc
internal class IndexControllerTest {
    @Autowired private lateinit var mockMvc: MockMvc

    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun `should show home view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
    }

    @Test
    fun `should show command-line view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc.perform(get("/command-line")).andExpect(status().isOk()).andExpect(view().name("command_line"))
    }

    @Test
    fun `should show form view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc
            .perform(
                get("/form"),
            ).andExpect(status().isOk())
            .andExpect(model().attributeExists("message"))
            .andExpect(view().name("form"))
    }

    @Test
    fun `should show batch view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc.perform(get("/batch")).andExpect(status().isOk()).andExpect(view().name("batch"))
    }

    @Test
    fun `should show contact view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc
            .perform(
                get("/contact"),
            ).andExpect(status().isOk())
            .andExpect(model().attributeExists("message"))
            .andExpect(view().name("contact"))
    }

    @Test
    fun `should show flyer view`(testInfo: TestInfo) {
        log.info(testInfo.displayName)
        mockMvc.perform(get("/flyer")).andExpect(status().isOk()).andExpect(view().name("flyer"))
    }
}
