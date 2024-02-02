package io.github.scordio.playground;

import org.springframework.data.repository.Repository;

import java.util.List;

interface BookRepository extends Repository<Book, Long> {

	Book save(Book book);

	List<Book> findAllByAuthorId(Long authorId);

}
