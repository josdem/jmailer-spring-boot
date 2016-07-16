/*
  Copyright 2016 José Luis De la Cruz Morales joseluis.delacruz@gmail.com

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

package com.jos.dem.jmailer.command

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import com.jos.dem.jmailer.enums.MessageType

@ApiModel(value="MessageCommand", description="Generic command to send emails")
class PostCommand implements Command {
  @ApiModelProperty(value = "Any valid email", allowableValues = "email@domain")
  String email
  @ApiModelProperty(value = "Email body", allowableValues = "text")
  String message
  @ApiModelProperty(value = "name", allowableValues = "Email receipt name")
  String name
  @ApiModelProperty(value = "Any valid email", allowableValues = "email@domain")
  String emailContact
  @ApiModelProperty(value = "source", allowableValues = "josdem.io,jmailer,gauthenticator")
  String source
  @ApiModelProperty(value = "type", allowableValues = "REGISTER")
  MessageType type
}

