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

package com.jos.dem.jmailer.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(value = "MessageCommand", description = "Generic command to send emails")
public class MessageCommand implements Command {

  @ApiModelProperty(value = "Any valid email", allowableValues = "email@domain")
  @Email
  @NotEmpty
  @NotNull
  String email;

  @ApiModelProperty(value = "Email body", allowableValues = "text")
  String message;

  @ApiModelProperty(value = "User's name", allowableValues = "text")
  String name;

  @ApiModelProperty(value = "Email body link", allowableValues = "text")
  String url;

  @ApiModelProperty(value = "Email source campaign", allowableValues = "text")
  String source;

  @ApiModelProperty(value = "After send email, url to redirect", allowableValues = "text")
  String redirect;

  @ApiModelProperty(value = "Any valid email", allowableValues = "email@domain")
  @Email
  String emailContact;

  @ApiModelProperty(value = "Email template", allowableValues = "text")
  String template;

  @ApiModelProperty(value = "Contact's name", allowableValues = "text")
  String contactName;

  @ApiModelProperty(value = "User's identifier", allowableValues = "text")
  String token;
}
