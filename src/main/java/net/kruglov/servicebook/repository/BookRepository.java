package net.kruglov.servicebook.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.Author;
import net.kruglov.servicebook.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    List<Book> findByGenre(String genre, Pageable pageable);

    List<Book> findByTitle(String title, Pageable pageable);

    List<Book> findByAuthors(Author author, Pageable pageable);

    List<Book> findByDescriptionContains(String description, Pageable pageable);

    List<Book> findByAuthorsLastName(String lastName, Pageable pageable);

    List<Book> findAll();

}
