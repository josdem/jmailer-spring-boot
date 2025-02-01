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

package com.josdem.jmailer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class IndexControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("showing home page")
  void shouldShowHomePage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
  }

  @Test
  @DisplayName("showing command line page")
  void shouldShowCommandLinePage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    mockMvc
        .perform(get("/command-line"))
        .andExpect(status().isOk())
        .andExpect(view().name("command_line"));
  }

  @Test
  @DisplayName("showing form page")
  void shouldShowFormPage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    mockMvc
        .perform(get("/form"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("message"))
        .andExpect(view().name("form"));
  }

  @Test
  @DisplayName("showing batch page")
  void shouldShowBatchPage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    mockMvc.perform(get("/batch")).andExpect(status().isOk()).andExpect(view().name("batch"));
  }

  @Test
  @DisplayName("showing contact page")
  void shouldShowContactPage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    mockMvc
        .perform(get("/contact"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("message"))
        .andExpect(view().name("contact"));
  }

  @Test
  @DisplayName("showing flyer page")
  void shouldShowFlyerPage(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc.perform(get("/flyer")).andExpect(status().isOk()).andExpect(view().name("flyer"));
  }
}
