/*
Copyright 2021 Jose Morales joseluis.delacruz@gmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.jos.dem.jmailer.collaborator;

import com.jos.dem.jmailer.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class CommandValidator {

  Logger log = LoggerFactory.getLogger(this.getClass());

  public Boolean isValid(Command command) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Command>> constraintViolations = validator.validate(command);
    log.info("violations:" + constraintViolations.size());
    if (!constraintViolations.isEmpty()) {
      log.info("field:" + constraintViolations.iterator().next().getPropertyPath());
      log.info("message:" + constraintViolations.iterator().next().getMessage());
    }
    return constraintViolations.isEmpty();
  }
}