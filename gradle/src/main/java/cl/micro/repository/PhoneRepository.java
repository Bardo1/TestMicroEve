package cl.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.micro.domain.Phone;
 
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
		
}
