package com.jos.dem.jmailer

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

import com.jos.dem.jmailer.controller.RequestInterceptor

import spock.lang.Specification

class RequestInterceptorSpec extends Specification {

  HandlerInterceptor interceptor = new RequestInterceptor('one,two', 'three', 'requestURL', 'token')

  void "should pass when request url is itself"(){
    given:"A request, response and object"
      HttpServletRequest request = Mock(HttpServletRequest)
      HttpServletResponse response = Mock(HttpServletResponse)
      Object handler = new Object()
    when:"We preHandle"
      request.requestURL >> new StringBuffer('requestURL')
      request.getHeader('X-Real-IP') >> 'realIP'
      Boolean result = interceptor.preHandle(request, response, handler)
    then:"We expect it pass"
      result
  }
}
