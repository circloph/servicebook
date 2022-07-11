package net.thumbtack.servicebook.repository;

import org.springframework.stereotype.Repository;
import net.thumbtack.servicebook.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
