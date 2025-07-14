package io.github.scordio.playground;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

@SpringBootTest
@Transactional
class MyTest {

	@SpringBootApplication
	static class TestApplication {

	}

	@Test
	void outerTest() {
		assertTrue(isActualTransactionActive()); // passes
	}

	@Nested
	class NestedClass implements TestCase, AnnotatedTestCase {

		@Test
		void innerTest() {
			assertTrue(isActualTransactionActive()); // passes
		}

	}

	interface TestCase {

		@Test
		default void inheritedTest() {
			assertTrue(isActualTransactionActive()); // fails
		}

	}

	@Transactional
	interface AnnotatedTestCase {

		@Test
		default void inheritedAnnotatedTest() {
			assertTrue(isActualTransactionActive()); // passes
		}

	}

}
