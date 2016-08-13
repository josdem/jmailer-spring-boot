package com.jos.dem.jmailer.service

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.jos.dem.jmailer.exception.EmailerException

@Service
class EmailerService {

  Logger log = LoggerFactory.getLogger(this.class)

  def sendEmail(){
    log.debug 'Sending email'
    throw new EmailerException()
  }

}
