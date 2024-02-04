package io.github.scordio.playground;

interface PersonSummary {

	String getFirstname();

	String getLastname();

	AddressSummary getAddress();

	interface AddressSummary {

		String getCity();

	}

}
