package scooter.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scooter.rental.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
