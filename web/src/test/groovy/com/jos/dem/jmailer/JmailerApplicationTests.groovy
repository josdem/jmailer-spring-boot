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

package com.jos.dem.jmailer

import static org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmailerApplication.class)
@WebAppConfiguration
class JmailerApplicationTests {

  @Autowired
  JavaMailSenderImpl javaMailSender

	@Test
	void shouldLoadContext() {
    assertEquals('smtp.gmail.com', javaMailSender.host)
    assertEquals(587, javaMailSender.port)
	}

}
