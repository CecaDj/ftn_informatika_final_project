package scooter.rental.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import scooter.rental.model.Scooter;
import scooter.rental.web.dto.ReturnScooterDto;

public interface ScooterService {
	
	Page<Scooter> getAll (Integer pageNo);
	Optional<Scooter> getOne(Long id);
	Scooter save (Scooter trotinet);
	Scooter update (Scooter trotinet);
	Scooter delete (Long id);
	Page<Scooter> search (Long addressId, Integer batteryLevelMin, Integer batteryLevelMax, Integer pageNo);
	Scooter returnScooter (ReturnScooterDto returnScooter);

}
