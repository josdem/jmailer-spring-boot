package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.MessageCommand

interface NotificationService {

  Boolean sendNotification(MessageCommand command)

}
