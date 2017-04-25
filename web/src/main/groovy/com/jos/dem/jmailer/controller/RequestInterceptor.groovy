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

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.annotation.PostConstruct

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerInterceptor implements HandlerInterceptor {

  @Value('${email.whitelist}')
  String emailWhiteList

  def whiteList = []

  Logger log = LoggerFactory.getLogger(this.class)

  @PostConstruct
  public void setup(){
    whiteList = emailWhiteList.tokenize(',')
  }

  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    def data = [:]
    data.remoteHost = request.remoteHost
    data.timeInMillis = System.currentTimeMillis()
    data.method = request.method
    data.requestURL = request.requestURL
    data.parameters = request.parameterMap

    if(!whiteList.contains(request.remoteHost)){
      data.warn = "UNAUTORIZED IP was detected in attempt to access to resource"
      log.info "data: ${data.dump()}"
      return false
    }

    loggerService.notifyRequest(data)
      return true
  }


  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
  }


  void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
  }

}
