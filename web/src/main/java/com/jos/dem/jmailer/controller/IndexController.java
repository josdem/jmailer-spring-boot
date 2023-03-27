/*
  Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.controller;

import com.jos.dem.jmailer.command.MessageCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

  @Value("${token}")
  private String token;

  @GetMapping("/")
  public String index() {
    log.info("Calling index");
    return "index";
  }

  @GetMapping("/command-line")
  public String commandLine() {
    log.info("Calling command line");
    return "command_line";
  }

  @GetMapping("/form")
  public ModelAndView form() {
    log.info("Calling contact");
    ModelAndView modelAndView = new ModelAndView("form");
    MessageCommand message = new MessageCommand();
    message.setToken(token);
    modelAndView.addObject("message", message);
    return modelAndView;
  }

  @GetMapping("/batch")
  public String batch() {
    log.info("Calling batch");
    return "batch";
  }

  @GetMapping("/contact")
  public ModelAndView contact() {
    log.info("Calling contact");
    ModelAndView modelAndView = new ModelAndView("contact");
    MessageCommand message = new MessageCommand();
    message.setToken(token);
    modelAndView.addObject("message", message);
    return modelAndView;
  }
}
