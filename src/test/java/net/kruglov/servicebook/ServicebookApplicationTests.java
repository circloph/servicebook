package net.kruglov.servicebook;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.kruglov.servicebook.model.Book;
import net.kruglov.servicebook.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServicebookApplicationTests {

	public ServicebookApplicationTests() {
	}

	@Autowired
	private BookRepository repository;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testJpa() {
		repository.save(new Book("gg", "gg", "gg", 2020));
		Book book = repository.findById(6).get();
		List<Book> books = repository.findAll();
	}

}
