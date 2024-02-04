package io.github.scordio.playground;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
class Person {

	@Id
	UUID id;

	String firstname, lastname;

	@Embedded
	Address address;

	static class Address {

		String zipCode, city, street;

	}

}
