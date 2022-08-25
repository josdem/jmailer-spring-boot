package com.jos.dem.jmailer.collaborator;

import org.springframework.stereotype.Component;

@Component
public class MessageValidator {

  private static final int MIN_LENGTH = 16;

  public boolean isValid(String message) {
    return message.length() <= MIN_LENGTH && !message.contains(" ");
  }
}
