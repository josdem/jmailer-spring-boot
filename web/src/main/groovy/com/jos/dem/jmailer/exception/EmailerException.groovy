package com.jos.dem.jmailer.exception

import java.lang.RuntimeException

class EmailerException extends RuntimeException {

  @Override
  String getMessage() {
    "Emailer exception"
  }

}
