package io.github.scordio.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(showSql = false) // use P6Spy
class BookRepositoryTest {

	@Autowired
	private BookRepository underTest;

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	void findAllByAuthorId() {
		// insert into author (name,id) values ('Homer',1);
		Author homer = authorRepository.save(new Author("Homer"));

		// insert into book (author_id,name,id) values (1,'Iliad',1);
		underTest.save(new Book("Iliad", homer));

		// insert into book (author_id,name,id) values (1,'Odyssey',2);
		underTest.save(new Book("Odyssey", homer));

		// select b1_0.id,b1_0.author_id,b1_0.name from book b1_0 left join author a1_0 on a1_0.id=b1_0.author_id where a1_0.id=1;
		underTest.findAllByAuthorId(homer.getId());

		// select b1_0.id,b1_0.author_id,b1_0.name from book b1_0 where b1_0.author_id=1;
		underTest.findAllByAuthorIdWithJPQL(homer.getId());
	}

}
