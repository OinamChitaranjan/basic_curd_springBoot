package org.coddingwallah.em.project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String>{
    Optional<EmployeeEntity> findByName(String name);

}
