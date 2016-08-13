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

import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.ModelAndView
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class HandlerException implements HandlerExceptionResolver {

  Logger log = LoggerFactory.getLogger(this.class)

  ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
    log.info ex.message
    def data = [:]
    data.message = ex.message
    ModelAndView modelAndView = new ModelAndView("error")
    modelAndView.addObject("data", data)
    modelAndView
  }
}
