package scooter.rental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scooter.rental.model.Address;
import scooter.rental.repository.AddressRepository;
import scooter.rental.service.AddressService;

@Service
public class JpaAddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address getOne(Long id) {
		return addressRepository.getOne(id);
	}

}
