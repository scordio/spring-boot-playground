package io.github.scordio.playground;

import org.springframework.data.repository.Repository;

interface AuthorRepository extends Repository<Author, Long> {

	Author save(Author author);

}
