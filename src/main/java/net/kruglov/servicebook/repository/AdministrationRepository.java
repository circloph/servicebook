package net.kruglov.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.Administration;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Integer> {
    
}
