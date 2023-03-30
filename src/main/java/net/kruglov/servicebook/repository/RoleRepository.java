package net.kruglov.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kruglov.servicebook.model.ERole;
import net.kruglov.servicebook.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(ERole name);
    
}
