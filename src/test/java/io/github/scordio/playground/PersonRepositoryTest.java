package io.github.scordio.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository underTest;

	@Test
	void test() {
		// select p1_0.id,p1_0.city,p1_0.street,p1_0.zip_code,p1_0.firstname,p1_0.lastname from person p1_0 where p1_0.lastname=?
		underTest.findByLastname("Smith");

		// select p1_0.firstname,p1_0.lastname from person p1_0 where p1_0.lastname=?
		underTest.findNamesOnlyByLastname("Smith");

		// select p1_0.firstname,p1_0.lastname,p1_0.city,p1_0.street,p1_0.zip_code from person p1_0 where p1_0.lastname=?
		underTest.findPersonSummaryByLastname("Smith");

		// select p1_0.firstname,p1_0.lastname,p1_0.city from person p1_0 where p1_0.lastname=?
		underTest.findPersonSummaryNoNestedByLastname("Smith");
	}

}
