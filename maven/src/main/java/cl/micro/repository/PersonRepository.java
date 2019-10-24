package cl.micro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import cl.micro.domain.Person;
 
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Person findByEmail(String email);	
	
	Optional<Person> findByName(String name);
	
}
