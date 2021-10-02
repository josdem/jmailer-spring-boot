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

package com.jos.dem.jmailer.controller;

import com.jos.dem.jmailer.command.MessageCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Value("${token}")
    private String token;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        logger.info("Calling index");
        return "index";
    }

    @RequestMapping("/command-line")
    public String commandLine() {
        logger.info("Calling command line");
        return "command_line";
    }

    @RequestMapping("/form")
     public ModelAndView form() {
        logger.info("Calling contact");
        ModelAndView modelAndView = new ModelAndView("form");
        MessageCommand message = new MessageCommand();
        message.setToken(token);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping("/batch")
    public String batch() {
        logger.info("Calling batch");
        return "batch";
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {
        logger.info("Calling contact");
        ModelAndView modelAndView = new ModelAndView("contact");
        MessageCommand message = new MessageCommand();
        message.setToken(token);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
