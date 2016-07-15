/*
  Copyright 2016 José Luis De la Cruz Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
class IndexController {

  Log log = LogFactory.getLog(this.class)

  @RequestMapping("/")
  String index() {
    log.info "Calling index"
    "index"
  }

  @RequestMapping("/command-line")
  String commandLine() {
    log.info "Calling command line"
    "command_line"
  }

  @RequestMapping("/form")
  String form() {
    log.info "Calling form"
    "form"
  }

  @RequestMapping("/batch")
  String batch() {
    log.info "Calling batch"
    "batch"
  }

  @RequestMapping("/contact")
  String contact() {
    log.info "Calling contact"
    "contact"
  }

}
