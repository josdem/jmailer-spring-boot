package com.jos.dem.jmailer.integration

interface MailService {

  Boolean sendMailWithTemplate(Map values, Map model, String template)

}
