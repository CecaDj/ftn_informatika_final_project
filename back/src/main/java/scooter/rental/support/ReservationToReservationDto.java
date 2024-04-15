package scooter.rental.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.Reservation;
import scooter.rental.web.dto.ReservationDto;

@Component
public class ReservationToReservationDto implements Converter<Reservation, ReservationDto>{

	@Override
	public ReservationDto convert(Reservation res) {
		ReservationDto dto = new ReservationDto(res.getId(), res.getEmail(), res.getScooter().getId());
		return dto;
	}
	
	

}
