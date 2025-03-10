/*
Copyright 2025 Jose Morales contact@josdem.io

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

package com.josdem.jmailer.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Message to send")
public class MessageCommand implements Command {

  @Schema(description = "Destination email's name")
  @NotNull
  private String name;

  @Schema(description = "Destination email")
  @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotEmpty
  private String email;

  @Schema(description = "Sever token")
  @NotNull
  private String token;

  @Schema(description = "Localization", type="string", allowableValues = {"English", "Spanish"})
  @NotNull
  private String locale;

  @Schema(description = "Message to send")
  @Size(min = 9)
  private String message;

  private String contactName;
  private String emailContact;

  @Schema(description = "FreeMarker template")
  @NotNull
  private String template;

  @Schema(description = "URL to redirect after send email")
  private String redirect;

  @Schema(description = "Application source name")
  private String source;
}
