/*
Copyright 2023 Jose Morales joseluis.delacruz@gmail.com

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

import com.jos.dem.jmailer.controller.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class EmailerConfigurerAdapter extends WebMvcConfigurerAdapter {

  @Value("${token}")
  String token;

  @Value("${email.whitelist}")
  String emailWhiteList;

  @Value("${email.blacklist}")
  String emailBlackList;

  @Value("${email.homeRequestURL}")
  String homeRequestURL;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(
            new RequestInterceptor(emailWhiteList, emailBlackList, homeRequestURL, token))
        .addPathPatterns("/**");
  }
}
