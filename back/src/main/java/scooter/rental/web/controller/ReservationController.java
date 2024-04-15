package scooter.rental.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scooter.rental.model.Reservation;
import scooter.rental.service.ReservationService;
import scooter.rental.support.ReservationDtoToReservation;
import scooter.rental.support.ReservationToReservationDto;
import scooter.rental.web.dto.ReservationDto;

@RestController
@RequestMapping (value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationDtoToReservation toReservation;
	
	@Autowired
	private ReservationToReservationDto toReservationDto;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDto> create(@RequestBody ReservationDto dto){
		
		Reservation res = toReservation.convert(dto);
		
		if(res.getScooter() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Reservation saved = reservationService.save(res);
		
		return new ResponseEntity<>(toReservationDto.convert(saved), HttpStatus.CREATED);		
	}
	
	


}
