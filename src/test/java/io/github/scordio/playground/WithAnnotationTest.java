package io.github.scordio.playground;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringJUnitConfig
@SpringBatchTest
class WithAnnotationTest {

	@Autowired
	ApplicationContext context;

	@Nested
	class Inner {

		@Autowired
		ApplicationContext context;

		@Test
		void test() {
			assertSame(WithAnnotationTest.this.context, context); // should succeed but fails
		}

	}

}
