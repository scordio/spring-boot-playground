package io.github.scordio.playground;

import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.UUID;

interface PersonRepository extends Repository<Person, UUID> {

	Collection<Person> findByLastname(String lastname);

	Collection<NamesOnly> findNamesOnlyByLastname(String lastname);

	Collection<PersonSummary> findPersonSummaryByLastname(String lastname);

	Collection<PersonSummaryNoNested> findPersonSummaryNoNestedByLastname(String lastname);

}
