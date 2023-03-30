package net.kruglov.servicebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.Book;
import net.kruglov.servicebook.model.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
    
    List<Reviews> findByBook(Book book);

}
