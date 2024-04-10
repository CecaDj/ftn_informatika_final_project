package scooter.rental.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import scooter.rental.model.Scooter;
import scooter.rental.repository.ScooterRepository;
import scooter.rental.service.ScooterService;

@Service
public class JpaScooterServiceImpl implements ScooterService {
	
	@Autowired
	private ScooterRepository scooterRepository;

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

}
