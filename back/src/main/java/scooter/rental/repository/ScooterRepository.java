package scooter.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scooter.rental.model.Scooter;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{
	

}
