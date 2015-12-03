package com.jos.dem.jmailer

import static org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSenderImpl

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmailerApplication.class)
class JmailerApplicationTests {

  @Autowired
  JavaMailSenderImpl javaMailSender

	@Test
	void shouldLoadContext() {
    assertEquals('smtp.gmail.com', javaMailSender.host)
    assertEquals(587, javaMailSender.port)
	}

}
