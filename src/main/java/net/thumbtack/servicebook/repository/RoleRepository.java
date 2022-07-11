package net.thumbtack.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.thumbtack.servicebook.model.ERole;
import net.thumbtack.servicebook.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(ERole name);
    
}
