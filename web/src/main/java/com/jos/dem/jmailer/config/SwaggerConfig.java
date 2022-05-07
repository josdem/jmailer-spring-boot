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

package com.jos.dem.jmailer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

  private final ApplicationProperties applicationProperties;

  @Bean
  public Docket createDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .protocols(Set.of("https", "http"))
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(applicationProperties.getBasePackage()))
            .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title(applicationProperties.getTitle())
            .description(applicationProperties.getDescription())
            .termsOfServiceUrl(applicationProperties.getTerms())
            .version(applicationProperties.getVersion())
            .build();
  }
}


