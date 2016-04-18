package com.jos.dem.jmailer.service

interface MailService {

  Boolean sendMailWithTemplate(Map values, Map model, String template)

}
