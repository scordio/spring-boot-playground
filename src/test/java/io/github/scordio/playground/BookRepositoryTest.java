package io.github.scordio.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

@DataJpaTest(showSql = false) // use P6Spy
class BookRepositoryTest {

	@Autowired
	private BookRepository underTest;

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	void findAllByAuthorId() {
		// insert into author (name, id) values ('Homer', 1);
		Author homer = authorRepository.save(new Author("Homer"));

		// insert into book (author_id, name, id) values (1, 'Iliad', 2);
		underTest.save(new Book("Iliad", homer));

		// insert into book (author_id, name, id) values (1, 'Odyssey', 3);
		underTest.save(new Book("Odyssey", homer));

		// select book0_.id as id1_1_, book0_.author_id as author_i3_1_, book0_.name as name2_1_ from book book0_ left outer join author author1_ on book0_.author_id=author1_.id where author1_.id=1;
		underTest.findAllByAuthorId(homer.getId());

		// select book0_.id as id1_1_, book0_.author_id as author_i3_1_, book0_.name as name2_1_ from book book0_ where book0_.author_id=1;
		underTest.findAllByAuthorIdWithJPQL(homer.getId());

		// select book0_.id as id1_1_, book0_.author_id as author_i3_1_, book0_.name as name2_1_ from book book0_ where book0_.author_id=1;
		underTest.findAll(byAuthorId(homer.getId()));
	}

	private static Specification<Book> byAuthorId(Long authorId) {
		return (root, query, builder) -> builder.equal(root.get("author").get("id"), authorId);
	}

}
