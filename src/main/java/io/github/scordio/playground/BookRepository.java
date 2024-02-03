package io.github.scordio.playground;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface BookRepository extends Repository<Book, Long> {

	Book save(Book book);

	List<Book> findAllByAuthorId(Long authorId);

	@Query("SELECT b FROM Book b WHERE b.author.id = :authorId")
	List<Book> findAllByAuthorIdWithJPQL(@Param("authorId") Long authorId);

}
