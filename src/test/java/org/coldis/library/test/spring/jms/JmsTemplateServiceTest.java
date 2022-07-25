package org.coldis.library.test.spring.jms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * JMS message converter test.
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class JmsTemplateServiceTest {

	/**
	 * Test service.
	 */
	@Autowired
	private JmsTemplateTestService jmsTemplateTestService;

	/**
	 * Tests duplicate id messages.
	 *
	 * @throws Exception If the test fails.
	 */
	@Test
	public void testDuplicateId() throws Exception {
		this.jmsTemplateTestService.sendMessage(JmsTemplateTestService.JMS_TEMPLATE_TEST_QUEUE, "message1", null, 30, null, true);
		this.jmsTemplateTestService.sendMessage(JmsTemplateTestService.JMS_TEMPLATE_TEST_QUEUE, "message2", null, 30, null, true);
		this.jmsTemplateTestService.sendMessage(JmsTemplateTestService.JMS_TEMPLATE_TEST_QUEUE, "message3", "teste2", 30, null, true);
		this.jmsTemplateTestService.sendMessage(JmsTemplateTestService.JMS_TEMPLATE_TEST_QUEUE, "message4", "teste2", 30, null, true);
		Thread.sleep(61000);
		Assertions.assertTrue(JmsTemplateTestService.ACKED_MESSAGES.contains("message1"));
		Assertions.assertTrue(JmsTemplateTestService.ACKED_MESSAGES.contains("message2"));
		Assertions.assertTrue(JmsTemplateTestService.ACKED_MESSAGES.contains("message3"));
		Assertions.assertTrue(JmsTemplateTestService.ACKED_MESSAGES.contains("message4"));
	}

}
