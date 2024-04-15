package scooter.rental.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import scooter.rental.model.Reservation;
import scooter.rental.model.Scooter;
import scooter.rental.repository.AddressRepository;
import scooter.rental.repository.ReservationRepository;
import scooter.rental.repository.ScooterRepository;
import scooter.rental.service.ScooterService;
import scooter.rental.web.dto.ReturnScooterDto;

@Service
public class JpaScooterServiceImpl implements ScooterService {
	
	@Autowired
	private ScooterRepository scooterRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Page<Scooter> getAll(Integer pageNo) {
		return scooterRepository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Optional<Scooter> getOne(Long id) {
		return scooterRepository.findById(id);
	}

	@Override
	public Scooter save(Scooter scooter) {
		scooter.setBatteryLevel(100);
		scooter.setRented(false);
		return scooterRepository.save(scooter);
	}

	@Override
	public Scooter update(Scooter scooter) {
		return scooterRepository.save(scooter);
	}

	@Override
	public Scooter delete(Long id) {
		Scooter deleted = getOne(id).get();
		
		if(deleted != null) {
			scooterRepository.delete(deleted);
			return deleted;
		}
		return null;
	}

	@Override
	public Page<Scooter> search(Long addressId, Integer batteryLevelMin, Integer batteryLevelMax, Integer pageNo) {
		return scooterRepository.search(addressId, batteryLevelMin, batteryLevelMax, PageRequest.of(pageNo, 2));
	}

	@Override
	public Scooter returnScooter(ReturnScooterDto returnScooter) {
		Scooter scooter = scooterRepository.getOne(returnScooter.getId());
		
		if(scooter != null) {
			Reservation reservation = null;
			
			for(Reservation res : scooter.getReservations()) {
				if(res.getReturnDate() == null) {
					reservation = res;
				}
			}
			
			if(reservation != null && reservation.getEmail().equals(returnScooter.getEmail())) {
			reservation.setReturnDate(LocalDateTime.now());
			scooter.setBatteryLevel(returnScooter.getBatteryLevel());
			scooter.setAddress(addressRepository.getOne(returnScooter.getAddressId()));
			scooter.setRented(false);
			scooterRepository.save(scooter);
			reservationRepository.save(reservation);		
			} else {
				return null;
			}
		}
		
		return scooter;
	}

}
