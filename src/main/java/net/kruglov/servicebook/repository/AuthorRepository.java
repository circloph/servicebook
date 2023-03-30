package net.kruglov.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByFirstNameAndLastNameAndMiddleName(String firstName, String middleName, String lastName);
    
}
