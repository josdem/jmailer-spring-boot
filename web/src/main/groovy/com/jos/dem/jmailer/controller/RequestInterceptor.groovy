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

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.annotation.PostConstruct

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RequestInterceptor implements HandlerInterceptor {

  def whiteList = []
  String token
  String blackList
  String homeRequestURL

  Logger log = LoggerFactory.getLogger(this.class)

  RequestInterceptor(String emailWhiteList, String emailBlackList, String homeRequestURL, String token){
    this.token = token
    this.blackList = emailBlackList
    this.homeRequestURL = homeRequestURL
    whiteList = emailWhiteList.tokenize(',')
  }

  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    def data = [:]
    data.remoteHost = request.remoteHost
    data.requestURL = request.requestURL
    data.referer = request.getHeader('Referer')
    data.realip = request.getHeader('X-Real-IP')
    data.host = request.getHeader('Host')
    data.realhost = request.getHeader('RealHost')
    data.auth = request.getHeader('Authorization')

    if(data.realip?.startsWith(blackList)){
      data.warn = "UNAUTORIZED IP was detected in attempt to access to resource"
      log.info "data: ${data.dump()}"
      return false
    }

    if( whiteList.contains(data.referer)
        || data.requestURL.toString().equals(homeRequestURL)
        || token.equals(data.auth) ){
      log.info "data: ${data.dump()}"
      return true
    }

    data.warn = "UNAUTORIZED request was detected in attempt to access to resource"
    log.info "data: ${data.dump()}"
    return false
  }


  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
  }


  void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
  }

}
