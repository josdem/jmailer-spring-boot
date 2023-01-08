/*
Copyright 2016 Jose Morales joseluis.delacruz@gmail.com

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

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

  private String token;
  private String blackList;
  private String homeRequestURL;

  public RequestInterceptor(
      String emailWhiteList, String emailBlackList, String homeRequestURL, String token) {
    this.token = token;
    this.blackList = emailBlackList;
    this.homeRequestURL = homeRequestURL;
  }

  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String remoteHost = request.getRemoteHost();
    String requestURL = request.getRequestURL().toString();
    String referer = request.getHeader("Referer");
    String realIp = request.getHeader("X-Real-IP");
    String host = request.getHeader("Host");
    String realHost = request.getHeader("RealHost");
    log.info("realIp: {}", realIp);
    log.info("realHost: {}", realHost);
    log.info("host: {}", host);
    log.info("referer: {}", referer);
    log.info("remoteHost: {}", remoteHost);
    log.info("requestURL: {}", requestURL);
    return true;
  }

  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {}

  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {}
}
