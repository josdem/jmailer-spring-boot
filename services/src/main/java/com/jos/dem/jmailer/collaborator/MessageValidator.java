package com.jos.dem.jmailer.collaborator;

import org.springframework.stereotype.Component;

@Component
public class MessageValidator {

  private static final int MIN_LENGTH = 16;
  private static final int MIN_UPPERCASE = 3;

  public boolean isValid(String message) {
    return message.length() <= MIN_LENGTH
        && message.length() - countUppercase(message) >= MIN_UPPERCASE
        && !message.contains(" ");
  }

  private int countUppercase(String message) {
    return (int) message.chars().filter(it -> it >= 65 && it <= 90).count();
  }
}
