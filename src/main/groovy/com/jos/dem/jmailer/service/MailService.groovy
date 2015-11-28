package com.jos.dem.jmailer.integration

interface MailService {

  void sendMailWithTemplate(Map values, Map model, String template)

}
