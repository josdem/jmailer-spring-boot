package com.jos.dem.jmailer.advice

import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

import com.jos.dem.jmailer.exception.BusinessException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Aspect
@Component
class AfterThrowingAdvice {

  Log log = LogFactory.getLog(this.class)

  @AfterThrowing(pointcut = "execution(* com.jos.dem.jmailer.service..**.*(..))", throwing = "ex")
  public void doRecoveryActions(RuntimeException ex) {
    log.info "Wrapping exception ${ex}"
    throw new BusinessException(ex.getMessage(), ex)
  }

}
