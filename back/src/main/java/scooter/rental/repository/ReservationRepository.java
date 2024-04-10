package scooter.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scooter.rental.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
