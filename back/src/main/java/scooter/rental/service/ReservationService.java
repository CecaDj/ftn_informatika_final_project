package scooter.rental.service;

import scooter.rental.model.Reservation;

public interface ReservationService {
	
	Reservation getOne(Long id);
	Reservation save(Reservation reservation);
}
