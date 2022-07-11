package net.thumbtack.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.thumbtack.servicebook.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
    
}
