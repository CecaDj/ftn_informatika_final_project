package scooter.rental.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scooter.rental.model.Reservation;
import scooter.rental.repository.ReservationRepository;
import scooter.rental.service.ReservationService;
@Service
public class JpaReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	

	@Override
	public Reservation getOne(Long id) {
		return reservationRepository.getOne(id);
	}

	@Override
	public Reservation save(Reservation reservation) {
		reservation.setRentingDate(LocalDateTime.now());
		reservation.getScooter().setRented(true);
		return reservationRepository.save(reservation);
	}



}
