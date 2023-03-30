package net.kruglov.servicebook.repository;

import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
