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
class WithDoubleAnnotationTest {

	@Autowired
	ApplicationContext context;

	@Nested
	@SpringBatchTest
	class Inner {

		@Autowired
		ApplicationContext context;

		@Test
		void test() {
			assertSame(WithDoubleAnnotationTest.this.context, context); // succeeds
		}

	}

}
