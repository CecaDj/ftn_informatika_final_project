package scooter.rental.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.Reservation;
import scooter.rental.service.ReservationService;
import scooter.rental.service.ScooterService;
import scooter.rental.web.dto.ReservationDto;

@Component
public class ReservationDtoToReservation implements Converter<ReservationDto, Reservation>{
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ScooterService scooterService;

	@Override
	public Reservation convert(ReservationDto dto) {
		Reservation reservation = null;
		
		if(dto.getId() == null) {
			reservation = new Reservation();
		}
		
		if(dto.getId() != null) {
			reservation = reservationService.getOne(dto.getId());
		}
		
		if(reservation != null) {
			reservation.setEmail(dto.getEmail());
			reservation.setScooter(scooterService.getOne(dto.getScooterId()).get());
		}
		
		return reservation;
	}
	

}
